package org.example;

public class Equipment {
    int idEquipment;
    String modelEquipment;

    public Equipment(int idEquipment, String modelEquipment) {
        this.idEquipment = idEquipment;
        this.modelEquipment = modelEquipment;
    }

    public int getIdEquipment() {
        return idEquipment;
    }

    public String getModelEquipment() {
        return modelEquipment;
    }
}
