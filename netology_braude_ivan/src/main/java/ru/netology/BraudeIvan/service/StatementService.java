package ru.netology.BraudeIvan.service;

import org.springframework.stereotype.Service;
import ru.netology.BraudeIvan.domain.operation.Operation;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatementService {

    private final Map<Integer, List<Operation>> customerOperationsMap = new HashMap<>();

    public void saveOperation(Operation operation) {
        List<Operation> operations = customerOperationsMap.computeIfAbsent(operation.getCustomerId(), k -> new ArrayList<>());
        operations.add(operation);
    }

    public List<Operation> getOperations(int customerId) {
        return customerOperationsMap.getOrDefault(customerId, new ArrayList<>());
    }

    public String getOperationsAsString() {
        return customerOperationsMap.toString();
    }

    public void removeOperation(int customerId, int operationId) {
        List<Operation> operations = customerOperationsMap.get(customerId);
        if (operations != null) {
            operations.removeIf(o -> o.getId() == operationId);
        }
    }
}
