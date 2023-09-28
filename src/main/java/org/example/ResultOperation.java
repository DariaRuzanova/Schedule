package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultOperation {
    private long time;
    private long duration;
    private int idOperation;
    private int idEmployee;
    private int idEquipment;
}
