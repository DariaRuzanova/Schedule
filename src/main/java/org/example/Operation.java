package org.example;

public class Operation {
    String operation;
    String precedingOperation;
    int durationOperation;
    int profitOperation;
    String modelEquipment;

    public Operation(String operation, String precedingOperation, int durationOperation, int profitOperation, String modelEquipment) {
        this.operation = operation;
        this.precedingOperation = precedingOperation;
        this.durationOperation = durationOperation;
        this.profitOperation = profitOperation;
        this.modelEquipment = modelEquipment;
    }
}
