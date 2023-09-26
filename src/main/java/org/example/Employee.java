package org.example;

import java.util.List;

public class Employee {
    int idEmployee;
    String name;
    Profession profession;
    List<Equipment>equipmentList;



    public Employee(int idEmployee, String name, Profession profession, List<Equipment> equipmentList) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.profession = profession;
        this.equipmentList = equipmentList;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public int getIdEmployee() {
        return idEmployee;
    }
}
