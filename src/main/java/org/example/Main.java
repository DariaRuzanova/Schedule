package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Equipment equipmentX = new Equipment(1,"X");
        Equipment equipmentY = new Equipment(2,"Y");
        Equipment equipmentZ = new Equipment(3,"Z");
        List<Equipment> listEquipment = Arrays.asList(equipmentX, equipmentY, equipmentZ);//лист оборудования

        Operation A = new Operation(1,"A", null, 5, 10, equipmentX.modelEquipment);
        Operation B = new Operation(2,"B", "A", 100, 50, equipmentY.modelEquipment);
        Operation C = new Operation(3,"C", null, 50, 80, equipmentZ.modelEquipment);
        Operation D = new Operation(4,"D", "A", 20, 20, equipmentX.modelEquipment);
        Operation E = new Operation(5,"E", "C", 80, 30, equipmentZ.modelEquipment);
        Operation F = new Operation(6,"F", null, 10, 5, equipmentX.modelEquipment);
        Operation G = new Operation(7,"G", "C", 30, 15, equipmentZ.modelEquipment);
        Operation H = new Operation(8,"H", null, 200, 35, equipmentY.modelEquipment);
        List<Operation> listOperation = Arrays.asList(A, B, C, D, E, F, G, H); //лист существующих операций

        Profession professionK = new Profession(1, "professionK");
        Profession professionN = new Profession(2, "professionN");
        Profession professionM = new Profession(3, "professionM");
        List<Profession> professionList = Arrays.asList(professionK, professionN, professionM);

        List<Equipment> equipmentEmp1 = Arrays.asList(equipmentX, equipmentY);
        List<Equipment> equipmentEmp2 = Arrays.asList(equipmentX);
        List<Equipment> equipmentEmp3 = Arrays.asList(equipmentY, equipmentZ);
        List<Equipment> equipmentEmp4 = Arrays.asList(equipmentZ);
        List<Equipment> equipmentEmp5 = Arrays.asList(equipmentY);

        Employee emp1 = new Employee(1,"Petr", professionK, equipmentEmp1);
        Employee emp2 = new Employee(2,"Ivan", professionN, equipmentEmp2);
        Employee emp3 = new Employee(3,"Fedor", professionM, equipmentEmp3);
        Employee emp4 = new Employee(4,"Semen", professionK, equipmentEmp4);
        Employee emp5 = new Employee(5,"Vlad", professionN, equipmentEmp5);
        List<Employee> employeeList = Arrays.asList(emp1, emp2, emp3, emp4, emp5);//лист работников

        Collections.sort(listOperation,new PrecedingOperationComparator());
        System.out.println(listOperation);
        int t =0;

        Map<Integer, Boolean> availabilityEquipment = new HashMap<>();
        for (int i = 0; i < listEquipment.size(); i++) {
            int key = listEquipment.get(i).idEquipment;
            availabilityEquipment.put(key,true);
        }
        Map<Integer,Boolean> availabilityEmployee = new HashMap<>();
        for (int j = 0; j < employeeList.size(); j++) {
            int key = employeeList.get(j).idEmployee;
            availabilityEmployee.put(key,true);
        }




    }
}