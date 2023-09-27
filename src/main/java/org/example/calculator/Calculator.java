package org.example.calculator;

import org.example.model.Employee;
import org.example.model.Equipment;
import org.example.model.Operation;
import org.example.model.SessionData;

import java.util.*;
import java.util.stream.Collectors;

public class Calculator {
    private SessionData data;
    private long currentTime;
    private List<Operation> orderedOperations;
    private Set<Integer> availabilityEquipment;
    private Set<Integer> availabilityEmployee;
    //    private List<Operation> operationsPerformed = new ArrayList<>();
    private Map<Integer, PerformedOperationEntity> performedOperations = new HashMap<>();

    public void calculate(SessionData data) {
        this.data = data;

        orderedOperations = data.getOperations().entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());
        orderedOperations.sort(new ProfitOperationComparator());

        availabilityEquipment = data.getEquipments().keySet();
        availabilityEmployee = data.getEmployees().keySet();

        currentTime = 0;
        while (!orderedOperations.isEmpty()) {
            startOperations(orderedOperations, availabilityEquipment, availabilityEmployee);
            waitCompletion(performedOperations,availabilityEquipment,availabilityEmployee);
        }
    }

    private void startOperations(List<Operation> orderedOperations, Set<Integer> availabilityEquipment, Set<Integer> availabilityEmployee) {
        for (int i = 0; i < orderedOperations.size(); i++) {
            int equipmentModelId = orderedOperations.get(i).getEquipmentModelId();
            int professionId = orderedOperations.get(i).getProfessionId();

            List<Employee> listEmployees = new ArrayList<>(data.getEmployees().values());
            listEmployees = listEmployees.stream()
                    .filter(x -> x.getProfessionId() == professionId && x.getEquipmentModelIds().contains(equipmentModelId))
                    .collect(Collectors.toList());
            List<Equipment> listEquipment = new ArrayList<>(data.getEquipments().values());
            List<Equipment> listEquipmentByModelId = listEquipment.stream()
                    .filter(x -> x.getEquipmentModelId() == equipmentModelId)
                    .collect(Collectors.toList());
            int equipmentIdForOperation = 0;
            int employeeIdForOperation = 0;
            for (Equipment equipment : listEquipmentByModelId) {
                if (availabilityEquipment.contains(equipment.getId())) {
                    equipmentIdForOperation = equipment.getId();
                    break;
                }
            }

            for (Employee employee : listEmployees) {
                if (availabilityEmployee.contains(employee.getId())) {
                    employeeIdForOperation = employee.getId();
                    break;
                }
            }
            if(availabilityEquipment.contains(equipmentIdForOperation)&&availabilityEmployee.contains(employeeIdForOperation)){
                availabilityEmployee.remove(employeeIdForOperation);
                availabilityEquipment.remove(equipmentIdForOperation);
                Operation operation = orderedOperations.get(i);
//            operationsPerformed.add(operation);

                PerformedOperationEntity performedOperationEntity =
                        new PerformedOperationEntity(operation.getId(), employeeIdForOperation, equipmentIdForOperation,
                                operation.getDuration(), operation.getProfit());

                performedOperations.put(performedOperationEntity.getDurationOperationRest(), performedOperationEntity);
            }
        }
    }

    private void waitCompletion(Map<Integer, PerformedOperationEntity> performedOperations,
                                Set<Integer> availabilityEquipment,
                                Set<Integer> availabilityEmployee) {
        Optional<Integer> minTime = performedOperations.keySet().stream().min(Comparator.naturalOrder());
        currentTime += minTime.get();
        PerformedOperationEntity finishedOperation = performedOperations.get(minTime.get());
        int equipmentFinishedId = finishedOperation.getIdEquipment();
        int employeeFinishedId = finishedOperation.getIdEmployee();


        availabilityEquipment.add(equipmentFinishedId);
        availabilityEmployee.add(employeeFinishedId);


    }

}

