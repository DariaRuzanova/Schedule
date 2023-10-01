package org.example.model;

import lombok.Data;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Data
public class SessionData {
    private final Map<Integer, EquipmentModel> equipmentModels = new HashMap<>();
    private final Map<Integer, Equipment> equipments = new HashMap<>();
    private final Map<Integer, Profession> professions = new HashMap<>();
    private final Map<Integer, Employee> employees = new HashMap<>();
    private final Map<Integer, Operation> operations = new HashMap<>();
    private final Period period = new Period(Instant.now(), Instant.now());
    private Strategy strategy;
}