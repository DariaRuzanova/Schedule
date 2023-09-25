package org.example;

public class Equipment {
    String modelEquipment;
    int count;

    public Equipment(String modelEquipment,int count) {
        this.modelEquipment = modelEquipment;
        this.count = count;
    }

    public String getModelEquipment() {
        return modelEquipment;
    }

    public void setModelEquipment(String modelEquipment) {
        this.modelEquipment = modelEquipment;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
