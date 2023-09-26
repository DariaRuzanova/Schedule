package org.example;

import org.example.calculator.Calculator;
import org.example.calculator.PerformOperation;
import org.example.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        SessionData data = GenerateTestData.generate();
        Calculator calculator = new Calculator();
        calculator.calculate(data);

        List<Operation> orderedOperations = data.getOperations().entrySet().stream().map(x->x.getValue()).collect(Collectors.toList());



        Map<Integer, Boolean> availabilityEquipment = new HashMap<>();
        for (int i = 0; i < listEquipment.size(); i++) {
            int key = listEquipment.get(i).id;
            availabilityEquipmentMap.put(key, true);
        }
        Map<Integer, Boolean> availabilityEmployeeMap = new HashMap<>();
        for (int j = 0; j < employeeList.size(); j++) {
            int key = employeeList.get(j).id;
            availabilityEmployeeMap.put(key, true);
        }
        Map<Integer, List<PerformOperation>> resultMap = new HashMap<>();
        List<Operation> listRest = new ArrayList<>();
        listRest.addAll(listOperation);
//        int counterTemps=0;
        Map<Integer, Integer> counterTempsForEquipmentMap = new HashMap<>();

        while (!listRest.isEmpty()) {

            for (int i = 0; i < listOperation.size(); i++) {
                Operation operation = listOperation.get(i);

                String modelEquipment = operation.getModelEquipment();
                int idEmployeeAvailable = checkAvailableEmployee(availabilityEmployeeMap, employeeList, modelEquipment);
                int idEquipmentAvailable = checkAvailableEquipment(availabilityEquipmentMap, listEquipment, modelEquipment);
                int counterTemps = counterTempsForEquipmentMap.getOrDefault(idEquipmentAvailable, 0);
                if (idEquipmentAvailable != 0 && idEmployeeAvailable != 0) {
                    counterTemps = counterTemps + operation.getDuration();
                    int profitOperationRes = operation.getProfit();
                    PerformOperation performOperation = new PerformOperation(operation.getId(),
                            operation.getName(), idEmployeeAvailable, idEquipmentAvailable, counterTemps, profitOperationRes);
                    List<PerformOperation> performOperationList = new ArrayList<>();
                    performOperationList = resultMap.get(idEquipmentAvailable);
                    performOperationList.add(performOperation);
                    resultMap.put(idEquipmentAvailable, performOperationList);
                    listRest.remove(operation);

                    if (counterTempsForEquipmentMap.getOrDefault(idEquipmentAvailable, 0) == 0) {
                        counterTempsForEquipmentMap.put(idEquipmentAvailable, counterTemps);
                    } else {
                        counterTempsForEquipmentMap.put(idEquipmentAvailable, counterTemps);
                    }

                    System.out.println(performOperation);
                }
            }
            availabilityEquipmentMap.replaceAll((k, v) -> !v);
            availabilityEmployeeMap.replaceAll((k, v) -> !v);
            listOperation.clear();
            listOperation.addAll(listRest);
        }
    }

    private static int checkAvailableEmployee(Map<Integer, Boolean> availabilityEmployeeMap,
                                              List<Employee> employeeList,
                                              String modelEquipment) {
        List<Employee> listEmployersResult = listEmployers(employeeList, modelEquipment);
        if (!listEmployersResult.isEmpty()) {
            for (Employee employee : listEmployersResult) {
                int idEmp = employee.getId();
                if (availabilityEmployeeMap.get(idEmp)) {
                    availabilityEmployeeMap.put(idEmp, false);
                    return idEmp;
                }

            }
            return 0;
        }
        return 0;
    }

    public static int checkAvailableEquipment(Map<Integer, Boolean> availabilityEquipmentMap,
                                              List<Equipment> equipmentList,
                                              String modelEquip) {
        int idEquipment = FindIdEquipment(equipmentList, modelEquip);

        if (availabilityEquipmentMap.get(idEquipment)) {
            availabilityEquipmentMap.put(idEquipment, false);
            return idEquipment;
        }
        return 0;

    }

    public static int FindIdEquipment(List<Equipment> equipmentList, String modelEquip) {
        List<Equipment> list = equipmentList.stream().filter(x -> x.model.equals(modelEquip)).toList();
        int id = 0;
        if (list.size() == 1) {
            id = list.get(0).getId();
        }
        return id;
    }

    public static List<Employee> listEmployers(List<Employee> employeeList, String modelEquip) {
        List<Employee> listEmployersResult = new ArrayList<>();
        for (Employee emp : employeeList) {
            List<Equipment> listEquipmentsEmployee = emp.getEquipmentList();
            Optional<Equipment> equipmentEmployee = listEquipmentsEmployee.stream().filter(x -> Objects.equals(x.getModel(), modelEquip)).findFirst();
//            for (Equipment i:listEquipmentsEmployee) {
//                if(i.getModelEquipment().equals(modelEquip)){
//                    listEmployersResult.add(emp);
//                }
//            }
            if (equipmentEmployee.isPresent()) {
                listEmployersResult.add(emp);
            }
        }
        return listEmployersResult;
    }


}


