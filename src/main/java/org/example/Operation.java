package org.example;

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


}
