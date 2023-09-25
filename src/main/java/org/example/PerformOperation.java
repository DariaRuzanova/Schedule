package org.example;

public class PerformOperation {
    int idOperation;
    String operation;
    Employee employee;
    int tempsOperation;
    int profitOperation;

    public PerformOperation(int idOperation, String operation, Employee employee, int tempsOperation, int profitOperation) {
        this.idOperation = idOperation;
        this.operation = operation;
        this.employee = employee;
        this.tempsOperation = tempsOperation;
        this.profitOperation = profitOperation;
    }
}
