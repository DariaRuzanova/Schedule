package org.example.service;

import org.example.calculator.Calculator;
import org.example.model.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class ServiceSchedule {
    private final SessionData data = new SessionData();
    private final Calculator calculator = new Calculator();

    public void uploadEquipment(List<Equipment> equipments) {
        Map<Integer, Equipment> equipmentsMap = data.getEquipments();
        equipmentsMap.clear();
        equipments.forEach(x -> equipmentsMap.put(x.getId(), x));

    }

    public void uploadEquipmentModels(List<EquipmentModel> equipmentModels) {
        Map<Integer, EquipmentModel> equipmentModelsMap = data.getEquipmentModels();
        equipmentModelsMap.clear();
        equipmentModels.forEach(x -> equipmentModelsMap.put(x.getId(), x));
    }

    public void uploadProfession(List<Profession> professions) {
        Map<Integer, Profession> professionMap = data.getProfessions();
        professionMap.clear();
        professions.forEach(x -> professionMap.put(x.getId(), x));
    }

    public void uploadEmployee(List<Employee> employees) {
        Map<Integer, Employee> employeeMap = data.getEmployees();
        employeeMap.clear();
        employees.forEach(x -> employeeMap.put(x.getId(), x));
    }

    public void uploadOperation(List<Operation> operations) {
        Map<Integer, Operation> operationMap = data.getOperations();
        operationMap.clear();
        operations.forEach(x -> operationMap.put(x.getId(), x));
    }

    public List<ResultSchedule> createSchedule(OperationParameters operationParameters) {
        Instant start = Instant.ofEpochSecond(operationParameters.getBegin());
        Instant end = Instant.ofEpochSecond(operationParameters.getEnd());
        Strategy strategy = operationParameters.getStrategy();

        data.getPeriod().setStartDate(start);
        data.getPeriod().setEndDate(end);
        data.setStrategy(strategy);
        return calculator.calculate(data);
    }
}
