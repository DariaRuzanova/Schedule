package org.example.calculator;

public class PerformOperation {
    int idOperation;
    String operation;
    int idEmployee;
    int idEquipment;
    int tempsOperationRes;
    int profitOperationRes;

    public PerformOperation(int idOperation, String operation, int idEmployee, int idEquipment, int tempsOperationRes, int profitOperationRes) {
        this.idOperation = idOperation;
        this.operation = operation;
        this.idEmployee = idEmployee;
        this.idEquipment = idEquipment;
        this.tempsOperationRes = tempsOperationRes;
        this.profitOperationRes = profitOperationRes;
    }
}
