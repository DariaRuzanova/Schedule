package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Equipment equipmentX = new Equipment("X", 2);
        Equipment equipmentY = new Equipment("Y", 2);
        Equipment equipmentZ = new Equipment("Z", 1);
        List<Equipment> listEquipment = Arrays.asList(equipmentX, equipmentY, equipmentZ);//лист оборудования

        Operation A = new Operation("A", null, 5, 10, equipmentX.modelEquipment);
        Operation B = new Operation("B", "A", 100, 50, equipmentY.modelEquipment);
        Operation C = new Operation("C", null, 50, 80, equipmentZ.modelEquipment);
        Operation D = new Operation("D", "A", 20, 20, equipmentX.modelEquipment);
        Operation E = new Operation("E", "C", 80, 30, equipmentZ.modelEquipment);
        Operation F = new Operation("F", null, 10, 5, equipmentX.modelEquipment);
        Operation G = new Operation("G", "C", 30, 15, equipmentZ.modelEquipment);
        Operation H = new Operation("H", null, 200, 35, equipmentY.modelEquipment);
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

        Employee emp1 = new Employee("Petr", professionK, equipmentEmp1);
        Employee emp2 = new Employee("Ivan", professionN, equipmentEmp2);
        Employee emp3 = new Employee("Fedor", professionM, equipmentEmp3);
        Employee emp4 = new Employee("Semen", professionK, equipmentEmp4);
        Employee emp5 = new Employee("Vlad", professionN, equipmentEmp5);
        List<Employee> employeeList = Arrays.asList(emp1, emp2, emp3, emp4, emp5);//лист работников


    }
}