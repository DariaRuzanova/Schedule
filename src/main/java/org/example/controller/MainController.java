package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.model.*;
import org.example.service.ServiceSchedule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
    private final ServiceSchedule serviceSchedule;

    public MainController(ServiceSchedule serviceSchedule) {
        this.serviceSchedule = serviceSchedule;
    }

    @PostMapping("/equipment")
    public ResponseEntity<String> uploadEquipment(@RequestBody List<Equipment> equipments) {
        serviceSchedule.uploadEquipment(equipments);
        return ResponseEntity.ok().body("Лист оборудования успешно загружен");
    }

    @PostMapping("/equipment_models")
    public ResponseEntity<String> uploadEquipmentModels(@RequestBody List<EquipmentModel> equipmentModels) {
        serviceSchedule.uploadEquipmentModels(equipmentModels);
        return ResponseEntity.ok().body("Лист моделей оборудования успешно загружен");
    }

    @PostMapping("/profession")
    public ResponseEntity<String> uploadProfession(@RequestBody List<Profession> professions) {
        serviceSchedule.uploadProfession(professions);
        return ResponseEntity.ok().body("Лист c профессиями успешно загружен");
    }

    @PostMapping("/employee")
    public ResponseEntity<String> uploadEmployee(@RequestBody List<Employee> employees) {
        serviceSchedule.uploadEmployee(employees);
        return ResponseEntity.ok().body("Лист рабочих загружен");
    }

    @PostMapping("/operation")
    public ResponseEntity<String> uploadOperation(@RequestBody List<Operation> operations) {
        serviceSchedule.uploadOperation(operations);
        return ResponseEntity.ok().body("Лист с операциями загружен");
    }

    @SneakyThrows
    @GetMapping("/create_schedule")
    public List<ResultSchedule> createSchedule(@RequestParam String operationParameters) {
        ObjectMapper mapper = new ObjectMapper();
        OperationParameters operationParametersObj = mapper.readValue(operationParameters, OperationParameters.class);
        return serviceSchedule.createSchedule(operationParametersObj);
    }
}
