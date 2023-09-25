package org.example;

import java.util.List;

public class Employee {
    String name;
    Profession profession;
    List<Equipment>equipmentList;

    public Employee(String name, Profession profession, List<Equipment> equipmentList) {
        this.name = name;
        this.profession = profession;
        this.equipmentList = equipmentList;
    }
}
