package org.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ScheduleApplication.class)
@AutoConfigureMockMvc
public class UploadEquipmentsTest {
    @Autowired
    private MockMvc mvc;

    @SneakyThrows
    @Test
    public void uploadEquipmentTest() {
        SessionData data = TestDataGenerator.generate();
        List<Equipment> equipments = data.getEquipments().values().stream().toList();
        String equipmentsJson = asJsonString(equipments);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/equipment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(equipmentsJson);
        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }
    @SneakyThrows
    @Test
    public void uploadEquipmentModelsTest() {
        SessionData data = TestDataGenerator.generate();
        List<EquipmentModel>equipmentModels=data.getEquipmentModels().values().stream().toList();
        String equipmentModelsJson = asJsonString(equipmentModels);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/equipment_models")
                .contentType(MediaType.APPLICATION_JSON)
                .content(equipmentModelsJson);
        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }

    @SneakyThrows
    @Test
    public void uploadProfessionTest() {
        SessionData data = TestDataGenerator.generate();
        List<Profession>professions=data.getProfessions().values().stream().toList();
        String professionJson = asJsonString(professions);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/profession")
                .contentType(MediaType.APPLICATION_JSON)
                .content(professionJson);
        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }
    @SneakyThrows
    @Test
    public void uploadEmployeeTest() {
        SessionData data = TestDataGenerator.generate();
        List<Employee>employees=data.getEmployees().values().stream().toList();
        String employeeJson = asJsonString(employees);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson);
        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }
    @SneakyThrows
    @Test
    public void uploadOperationTest() {
        SessionData data = TestDataGenerator.generate();
        List<Operation>operations=data.getOperations().values().stream().toList();
        String employeeJson = asJsonString(operations);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/operation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson);
        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
