package org.example;

import org.example.model.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class TestDataGenerator {
    public static SessionData generate(){
        SessionData result = new SessionData();
        fillEquipmentModels(result);
        fillEquipment(result);
        fillProfession(result);
        fillEmployee(result);
        fillOperation(result);
        fillPeriod(result);
        result.setStrategy(Strategy.AS_SOON_AS_POSSIBLE);

        return result;
    }

    private static void fillPeriod(SessionData data){
        Instant startTime = Instant.parse("2023-07-11T08:00:30Z");
        Instant endTime = Instant.parse("2023-10-11T08:00:30Z");
        data.getPeriod().setStartDate(startTime);
        data.getPeriod().setEndDate(endTime);
    }

    private static void fillEquipmentModels(SessionData data){
        EquipmentModel equipmentX = new EquipmentModel(1, "X");
        EquipmentModel equipmentY = new EquipmentModel(2, "Y");
        EquipmentModel equipmentZ = new EquipmentModel(3, "Z");

        data.getEquipmentModels().put(equipmentX.getId(), equipmentX);
        data.getEquipmentModels().put(equipmentY.getId(), equipmentY);
        data.getEquipmentModels().put(equipmentZ.getId(), equipmentZ);
    }
    private static void fillEquipment(SessionData data){
        Equipment equipment1 = new Equipment(1,1,"equipmentX");
        Equipment equipment2 = new Equipment(2,2,"equipmentY");
        Equipment equipment3 = new Equipment(3,3,"equipmentZ");
        Equipment equipment4 = new Equipment(4,1,"equipmentX");
        Equipment equipment5 = new Equipment(5,3,"equipmentX");

        data.getEquipments().put(equipment1.getId(),equipment1);
        data.getEquipments().put(equipment2.getId(), equipment2);
        data.getEquipments().put(equipment3.getId(),equipment3);
        data.getEquipments().put(equipment4.getId(),equipment4);
        data.getEquipments().put(equipment4.getId(),equipment5);
    }
    private static void fillProfession(SessionData data){
        Profession professionX = new Profession(1, "professionX");
        Profession professionY = new Profession(2, "professionY");
        Profession professionZ = new Profession(3, "professionZ");

        data.getProfessions().put(professionX.getId(),professionX);
        data.getProfessions().put(professionY.getId(),professionY);
        data.getProfessions().put(professionZ.getId(),professionZ);
    }

    private static void fillEmployee(SessionData data){
        Employee employee1 = new Employee(1,"emp1",1, Arrays.asList(1,4));
        Employee employee2 = new Employee(2,"emp2",2, List.of(2));
        Employee employee3 = new Employee(3,"emp3",3, Arrays.asList(3,5));
        Employee employee4 = new Employee(4,"emp4",3, Arrays.asList(3,5));

        data.getEmployees().put(employee1.getId(),employee1);
        data.getEmployees().put(employee2.getId(),employee2);
        data.getEmployees().put(employee3.getId(),employee3);
        data.getEmployees().put(employee4.getId(),employee4);
    }

    private static void fillOperation(SessionData data){
        Operation A = new Operation(1,"A",null,1,1,5,10);
        Operation B = new Operation(2,"B",1,2,2,15,50);
        Operation C = new Operation(3,"C",null,3,3,50,80);
        Operation D = new Operation(4,"D",1,1,1,20,100);
        Operation E = new Operation(5,"E",3,2,2,40,50);
        Operation F = new Operation(6,"F",null,2,2,60,500);
        Operation G = new Operation(7,"G",3,1,1,10,10);
        Operation H = new Operation(8,"H",null,2,2,30,120);

        data.getOperations().put(A.getId(),A);
        data.getOperations().put(B.getId(), B);
        data.getOperations().put(C.getId(),C);
        data.getOperations().put(D.getId(), D);
        data.getOperations().put(E.getId(), E);
        data.getOperations().put(F.getId(), F);
        data.getOperations().put(G.getId(), G);
        data.getOperations().put(H.getId(), H);
    }
}
