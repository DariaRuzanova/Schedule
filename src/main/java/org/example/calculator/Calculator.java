package org.example.calculator;

import org.example.model.ResultOperation;
import org.example.model.ResultSchedule;
import org.example.model.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class Calculator {
    private SessionData data;

    private long currentTime;
    private List<Operation> orderedOperations;
    private final Map<Integer, Integer> availabilityEquipment = new HashMap<>();
    private final Map<Integer, Integer> availabilityEmployee = new HashMap<>();
    private final List<RunningOperationEntity> runningOperations = new ArrayList<>();
    private final List<ResultOperation> orderExecution = new ArrayList<>();
    private final List<ResultSchedule> schedule = new ArrayList<>();

    public void calculate(SessionData data) {
        this.data = data;

        orderedOperations = new ArrayList<>(data.getOperations().values());
        orderedOperations.sort(new ProfitOperationComparator());

        data.getEquipments().keySet().forEach(x -> availabilityEquipment.put(x, x));
        data.getEmployees().keySet().forEach(x -> availabilityEmployee.put(x, x));

        currentTime = 0;
        while (startOperations()) {
            waitCompletion();
        }

        defineTimes();
        System.out.println();
        schedule.forEach(System.out::println);
    }


    private boolean startOperations() {
        List<Operation> restOrderedOperation = new ArrayList<>(orderedOperations);

        for (Operation operation : restOrderedOperation) {

//            Operation operation = orderedOperations.get(i);
            int equipmentModelId = operation.getEquipmentModelId();
            int professionId = operation.getProfessionId();

            Optional<Employee> employeeForOperation = data.getEmployees().values().stream()
                    .filter(x -> availabilityEmployee.containsKey(x.getId())
                            && x.getProfessionId() == professionId
                            && x.getEquipmentModelIds().contains(equipmentModelId))
                    .findFirst();

            Optional<Equipment> equipmentForOperation = data.getEquipments().values().stream()
                    .filter(x -> availabilityEquipment.containsKey(x.getId())
                            && x.getEquipmentModelId() == equipmentModelId)
                    .findFirst();

            boolean isChain = orderedOperations.stream().noneMatch(x -> ((Integer) x.getId()).equals(operation.getPrecedingOperationId()));

            if (employeeForOperation.isPresent()
                    && equipmentForOperation.isPresent()
                    && isChain
            ) {
                availabilityEmployee.remove(employeeForOperation.get().getId());
                availabilityEquipment.remove(equipmentForOperation.get().getId());
                orderedOperations.remove(operation);

                RunningOperationEntity performedOperationEntity =
                        new RunningOperationEntity(operation.getId(),
                                employeeForOperation.get().getId(),
                                equipmentForOperation.get().getId(),
                                operation.getDuration(),
                                operation.getProfit());
                runningOperations.add(performedOperationEntity);

                orderExecution.add(new ResultOperation(currentTime,
                        operation.getDuration(),
                        operation.getId(),
                        employeeForOperation.get().getId(),
                        equipmentForOperation.get().getId()));
            }
        }
        return !runningOperations.isEmpty();
    }

    private void waitCompletion() {
        Optional<RunningOperationEntity> minTime = runningOperations.stream().min(Comparator.comparing(RunningOperationEntity::getDurationOperationRest));
        if (minTime.isPresent()) {
            currentTime += minTime.get().durationOperationRest;
            runningOperations.forEach(x -> x.durationOperationRest = x.durationOperationRest - minTime.get().durationOperationRest);
            List<RunningOperationEntity> closedOperations = runningOperations.stream().filter(x -> x.durationOperationRest <= 0).collect(Collectors.toList());
            for (RunningOperationEntity closedOperation : closedOperations) {
                availabilityEmployee.put(closedOperation.idEmployee, closedOperation.idEmployee);
                availabilityEquipment.put(closedOperation.idEquipment, closedOperation.idEquipment);
            }
            runningOperations.removeAll(closedOperations);
        }
    }

    private void defineTimes() {
        Period period = data.getPeriod();
        Instant startTimeNew;
        switch (data.getStrategy()) {
            case AS_SOON_AS_POSSIBLE:
                startTimeNew = period.getStartDate();
                break;
            case JUST_IN_TIME:
                Instant endTime = period.getEndDate();
                Optional<Long> maxTime = orderExecution.stream().map(x -> x.getDuration() + x.getTime()).max(Comparator.comparingLong(x -> x));
                startTimeNew = endTime.minusSeconds(maxTime.get() * 60);
                break;
            default:
                startTimeNew = Instant.now();
        }
        for (ResultOperation operation : orderExecution) {
            Instant startOperation = startTimeNew.plusSeconds(operation.getTime() * 60);
            ResultSchedule resultScheduleOperation = new ResultSchedule(startOperation, operation);
            schedule.add(resultScheduleOperation);
        }
    }
}

