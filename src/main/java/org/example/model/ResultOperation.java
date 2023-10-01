package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultOperation {
    private long time;
    private long duration;
    private int idOperation;
    private int idEmployee;
    private int idEquipment;
}
