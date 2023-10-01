package org.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.example.model.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ScheduleApplication.class)
@AutoConfigureMockMvc
public class UploadEquipmentsTest {
    @Autowired
    private MockMvc mvc;

    private final SessionData data = TestDataGenerator.generate();

    public MvcResult uploadEquipment() throws Exception {
        List<Equipment> equipments = data.getEquipments().values().stream().toList();
        String equipmentsJson = asJsonString(equipments);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/equipment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(equipmentsJson);
        return mvc.perform(request).andReturn();
    }

    public MvcResult uploadEquipmentModels() throws Exception {
        SessionData data = TestDataGenerator.generate();
        List<EquipmentModel> equipmentModels = data.getEquipmentModels().values().stream().toList();
        String equipmentModelsJson = asJsonString(equipmentModels);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/equipment_models")
                .contentType(MediaType.APPLICATION_JSON)
                .content(equipmentModelsJson);
        return mvc.perform(request).andReturn();

    }

    public MvcResult uploadProfessionModels() throws Exception {
        SessionData data = TestDataGenerator.generate();
        List<Profession> professions = data.getProfessions().values().stream().toList();
        String professionJson = asJsonString(professions);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/profession")
                .contentType(MediaType.APPLICATION_JSON)
                .content(professionJson);
        return mvc.perform(request).andReturn();

    }

    public MvcResult uploadEmployee() throws Exception {
        SessionData data = TestDataGenerator.generate();
        List<Employee> employees = data.getEmployees().values().stream().toList();
        String employeeJson = asJsonString(employees);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson);
        return mvc.perform(request).andReturn();
    }

    public MvcResult uploadOperation() throws Exception {
        SessionData data = TestDataGenerator.generate();
        List<Operation> operations = data.getOperations().values().stream().toList();
        String employeeJson = asJsonString(operations);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/operation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson);
        return mvc.perform(request).andReturn();
    }

    @SneakyThrows
    @Test
    public void uploadEquipmentTest() {
        MvcResult result = uploadEquipment();
        Assertions.assertEquals(result.getResponse().getStatus(), 200);
    }

    @SneakyThrows
    @Test
    public void uploadEquipmentModelsTest() {
        MvcResult result = uploadEquipmentModels();
        Assertions.assertEquals(result.getResponse().getStatus(), 200);
    }


    @SneakyThrows
    @Test
    public void uploadProfessionTest() {
        MvcResult result = uploadProfessionModels();
        Assertions.assertEquals(result.getResponse().getStatus(), 200);

    }


    @SneakyThrows
    @Test
    public void uploadEmployeeTest() {
        MvcResult result = uploadEmployee();
        Assertions.assertEquals(result.getResponse().getStatus(), 200);
    }

    @SneakyThrows
    @Test
    public void uploadOperationTest() {
        MvcResult result = uploadOperation();
        Assertions.assertEquals(result.getResponse().getStatus(), 200);
    }

    @Test
    public void createSchedule() throws Exception {
        uploadEquipment();
        uploadEquipmentModels();
        uploadProfessionModels();
        uploadEmployee();
        uploadOperation();

        OperationParameters operationParameters = new OperationParameters(data.getStrategy(),
                data.getPeriod().getStartDate().getEpochSecond(),
                data.getPeriod().getEndDate().getEpochSecond());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/create_schedule")
                .queryParam("operationParameters", asJsonString(operationParameters));
        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        List<ResultSchedule> listFiles = mapper.readValue(response, new TypeReference<>() {
        });
        assertFalse(listFiles.isEmpty());
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
