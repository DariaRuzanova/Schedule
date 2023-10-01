package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Operation {
    private int id;
    private String name;
    private Integer precedingOperationId;
    private int professionId;
    private int equipmentModelId;
    private int duration;
    private int profit;
}
