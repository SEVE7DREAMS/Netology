package ru.netology.BraudeIvan.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.BraudeIvan.configuration.OperationProperties;
import ru.netology.BraudeIvan.controller.OperationController;
import ru.netology.BraudeIvan.controller.DTO.OperationDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OperationControllerTest {
    @Autowired
    private OperationController operationController;
    @Autowired
    private OperationProperties properties;

    @Test
    void addOperationsTest() throws InterruptedException {
        OperationDTO dto = new OperationDTO(1, 1, 100, "RUB", "uplay");
        operationController.addOperation(dto);

        Thread.sleep(properties.getTimeout() + 100); // Add a little extra time to ensure the operation is processed

        List<OperationDTO> operations = operationController.getOperationsByUser(1);
        assertTrue(operations.contains(dto));
    }

    @Test
    void getOperationsTest() throws InterruptedException {
        OperationDTO operation1 = new OperationDTO(2, 1, 100, "RUB", "uplay");
        operationController.addOperation(operation1);
        Thread.sleep(properties.getTimeout() + 100);
        OperationDTO operation2 = new OperationDTO(3, 1, 200, "RUB", "uplay");
        operationController.addOperation(operation2);
        Thread.sleep(properties.getTimeout() + 100);

        List<OperationDTO> operations = operationController.getOperationsByUser(1);
        assertTrue(operations.contains(operation1));
        assertTrue(operations.contains(operation2));
    }

    @Test
    void removeOperationsTest() throws InterruptedException {
        OperationDTO operation1 = new OperationDTO(4, 1, 100, "RUB", "uplay");
        operationController.addOperation(operation1);
        Thread.sleep(properties.getTimeout() + 100);
        int oldSize = operationController.getOperationsByUser(1).size();

        operationController.removeOperation(1, 4);
        Thread.sleep(properties.getTimeout() + 100);
        int new_size = operationController.getOperationsByUser(1).size();

        assertEquals(1, oldSize - new_size);
    }

    @Test
    void addMultipleOperationsTest() throws InterruptedException {
        OperationDTO operation1 = new OperationDTO(5, 2, 150, "RUB", "Shop");
        OperationDTO operation2 = new OperationDTO(6, 2, 200, "RUB", "Market");
        operationController.addOperation(operation1);
        operationController.addOperation(operation2);
        Thread.sleep(properties.getTimeout() + 100);

        List<OperationDTO> operations = operationController.getOperationsByUser(2);
        assertTrue(operations.contains(operation1));
        assertTrue(operations.contains(operation2));
    }
}
