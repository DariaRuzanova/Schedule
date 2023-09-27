package org.example.calculator;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PerformedOperationEntity {

    int idOperation;
    int idEmployee;
    int idEquipment;
    int durationOperationRest;
    int profitOperation;

}
