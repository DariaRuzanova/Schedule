package org.example;

import java.util.Comparator;

public class Operation  {
    int idOperation;
    String operation;

    public int getIdOperation() {
        return idOperation;
    }

    public String getModelEquipment() {
        return modelEquipment;
    }

    String precedingOperation;
    int durationOperation;
    int profitOperation;
    String modelEquipment;

    public Operation(int idOperation, String operation, String precedingOperation, int durationOperation, int profitOperation, String modelEquipment) {
        this.idOperation = idOperation;
        this.operation = operation;
        this.precedingOperation = precedingOperation;
        this.durationOperation = durationOperation;
        this.profitOperation = profitOperation;
        this.modelEquipment = modelEquipment;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "idOperation=" + idOperation +
                ", operation='" + operation + '\'' +
                ", precedingOperation='" + precedingOperation + '\'' +
                ", durationOperation=" + durationOperation +
                ", profitOperation=" + profitOperation +
                ", modelEquipment='" + modelEquipment + '\'' +
                '}';
    }

}
