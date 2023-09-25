package org.example;

import java.util.Comparator;

public class PrecedingOperationComparator implements Comparator<Operation> {
    @Override
    public int compare(Operation o1, Operation o2) {
        return o1.precedingOperation.compareTo(o2.precedingOperation);
    }

}
