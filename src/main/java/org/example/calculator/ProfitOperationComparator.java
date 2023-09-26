package org.example.calculator;

import org.example.model.Operation;

import java.util.Comparator;

public class ProfitOperationComparator implements Comparator<Operation> {
    @Override
    public int compare(Operation o1, Operation o2) {
        if (o1.getPrecedingOperationId() == null && o2.getPrecedingOperationId() == null) {
            return -0;
        }
        if (o1.getPrecedingOperationId() == null) {
            return -1;
        }
        if (o2.getPrecedingOperationId() == null) {
            return 1;
        }

        double s1 = (double) o1.getProfit() / (double) o1.getDuration();
        double s2 = (double) o2.getProfit() / (double) o2.getDuration();
        return Double.compare(s1, s2);
    }
}
