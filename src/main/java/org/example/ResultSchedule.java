package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultSchedule {
    private long start;
    private int duration;
    private int idOperation;
    private int idEmployee;
    private int idEquipment;
}
