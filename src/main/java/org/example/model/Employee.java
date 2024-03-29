package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@AllArgsConstructor
@Getter
public class Employee {
    private int id;
    private String name;
    private int professionId;
    List<Integer> equipmentModelIds;
}
