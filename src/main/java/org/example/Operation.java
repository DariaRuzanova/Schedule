package org.example;

import java.util.Comparator;

public class Operation  {
    int idOperation;
    String operation;

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
    public int getIdOperation() {
        return idOperation;
    }

    public String getModelEquipment() {
        return modelEquipment;
    }

    public String getOperation() {
        return operation;
    }

    public int getDurationOperation() {
        return durationOperation;
    }

    public int getProfitOperation() {
        return profitOperation;
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
