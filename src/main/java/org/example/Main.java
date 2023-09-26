package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Equipment equipmentX = new Equipment(1, "X");
        Equipment equipmentY = new Equipment(2, "Y");
        Equipment equipmentZ = new Equipment(3, "Z");
        List<Equipment> listEquipment = Arrays.asList(equipmentX, equipmentY, equipmentZ);//лист оборудования

        Operation A = new Operation(1, "A", null, 5, 10, equipmentX.modelEquipment);
        Operation B = new Operation(2, "B", "A", 100, 50, equipmentY.modelEquipment);
        Operation C = new Operation(3, "C", null, 50, 80, equipmentZ.modelEquipment);
        Operation D = new Operation(4, "D", "A", 20, 20, equipmentX.modelEquipment);
        Operation E = new Operation(5, "E", "C", 80, 30, equipmentZ.modelEquipment);
        Operation F = new Operation(6, "F", null, 10, 5, equipmentX.modelEquipment);
        Operation G = new Operation(7, "G", "C", 30, 15, equipmentZ.modelEquipment);
        Operation H = new Operation(8, "H", null, 200, 35, equipmentY.modelEquipment);
        List<Operation> listOperation = new ArrayList<>();
        listOperation.add(A);
        listOperation.add(B);
        listOperation.add(C);
        listOperation.add(D);
        listOperation.add(E);
        listOperation.add(F);
        listOperation.add(G);
        listOperation.add(H);
//        = Arrays.asList(A, B, C, D, E, F, G, H); //лист существующих операций

        Profession professionK = new Profession(1, "professionK");
        Profession professionN = new Profession(2, "professionN");
        Profession professionM = new Profession(3, "professionM");
        List<Profession> professionList = Arrays.asList(professionK, professionN, professionM);

        List<Equipment> equipmentEmp1 = Arrays.asList(equipmentX, equipmentY);
        List<Equipment> equipmentEmp2 = Arrays.asList(equipmentX);
        List<Equipment> equipmentEmp3 = Arrays.asList(equipmentY, equipmentZ);
        List<Equipment> equipmentEmp4 = Arrays.asList(equipmentZ);
        List<Equipment> equipmentEmp5 = Arrays.asList(equipmentY);

        Employee emp1 = new Employee(1, "Petr", professionK, equipmentEmp1);
        Employee emp2 = new Employee(2, "Ivan", professionN, equipmentEmp2);
        Employee emp3 = new Employee(3, "Fedor", professionM, equipmentEmp3);
        Employee emp4 = new Employee(4, "Semen", professionK, equipmentEmp4);
        Employee emp5 = new Employee(5, "Vlad", professionN, equipmentEmp5);
        List<Employee> employeeList = Arrays.asList(emp1, emp2, emp3, emp4, emp5);//лист работников

        Collections.sort(listOperation, new PrecedingOperationComparator());

        Map<Integer, Boolean> availabilityEquipmentMap = new HashMap<>();
        for (int i = 0; i < listEquipment.size(); i++) {
            int key = listEquipment.get(i).idEquipment;
            availabilityEquipmentMap.put(key, true);
        }
        Map<Integer, Boolean> availabilityEmployeeMap = new HashMap<>();
        for (int j = 0; j < employeeList.size(); j++) {
            int key = employeeList.get(j).idEmployee;
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
                    counterTemps = counterTemps + operation.getDurationOperation();
                    int profitOperationRes = operation.getProfitOperation();
                    PerformOperation performOperation = new PerformOperation(operation.getIdOperation(),
                            operation.getOperation(), idEmployeeAvailable, idEquipmentAvailable, counterTemps, profitOperationRes);
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
                int idEmp = employee.getIdEmployee();
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
        List<Equipment> list = equipmentList.stream().filter(x -> x.modelEquipment.equals(modelEquip)).toList();
        int id = 0;
        if (list.size() == 1) {
            id = list.get(0).getIdEquipment();
        }
        return id;
    }

    public static List<Employee> listEmployers(List<Employee> employeeList, String modelEquip) {
        List<Employee> listEmployersResult = new ArrayList<>();
        for (Employee emp : employeeList) {
            List<Equipment> listEquipmentsEmployee = emp.getEquipmentList();
            Optional<Equipment> equipmentEmployee = listEquipmentsEmployee.stream().filter(x -> Objects.equals(x.getModelEquipment(), modelEquip)).findFirst();
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


