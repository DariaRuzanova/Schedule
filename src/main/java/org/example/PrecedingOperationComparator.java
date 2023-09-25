package org.example;

import java.util.Comparator;

public class PrecedingOperationComparator implements Comparator<Operation> {
    @Override
    public int compare(Operation o1, Operation o2) {
        if(o1.precedingOperation==null && o2.precedingOperation==null){
            return -0;
        }
        if(o1.precedingOperation==null){
            return -1;
        }
        if(o2.precedingOperation==null){
            return 1;
        }
        return 0;
    }


}
