package org.example.calculator;

import org.example.model.Operation;
import org.example.model.SessionData;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Calculator {
    private SessionData data;
    private long currentTime;
    private List<Operation> orderedOperations;
    private Set<Integer> availabilityEquipment;

    public void calculate(SessionData data) {
        this.data = data;

        orderedOperations = data.getOperations().entrySet().stream().map(x->x.getValue()).collect(Collectors.toList());
        orderedOperations.sort(new ProfitOperationComparator());

        availabilityEquipment = data.getEquipments().keySet();

        currentTime = 0;
        while(!orderedOperations.isEmpty()) {
            startOperations();
            waitCompletion();
        }
    }

    private void startOperations() {
    }

    private void waitCompletion() {
    }
}
