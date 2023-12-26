package ru.netology.BraudeIvan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.netology.BraudeIvan.domain.operation.Operation;
import ru.netology.BraudeIvan.service.AsyncInputOperationService;
import ru.netology.BraudeIvan.service.StatementService;
import ru.netology.BraudeIvan.controller.DTO.OperationDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class OperationController {

    private final AsyncInputOperationService inputOperationService;
    private final StatementService statementService;

    @GetMapping("{customerId}")
    public List<OperationDTO> getOperationsByUser(@PathVariable("Id") int customerId) {
        List<Operation> operations = statementService.getOperations(customerId);
        return operations.stream()
                .map(o -> new OperationDTO(o.getId(), o.getCustomerId(), o.getSum(), o.getCurrency(), o.getMerchant()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addOperation(@RequestBody OperationDTO operationDto) {
        Operation operation = new Operation(
                operationDto.getId(),
                operationDto.getCustomerId(),
                operationDto.getSum(),
                operationDto.getCurrency(),
                operationDto.getMerchant());

        inputOperationService.addOperation(operation);
    }

    @DeleteMapping("/delete")
    public void removeOperation(@PathVariable int customerId, @RequestParam int operationId) {
        statementService.removeOperation(customerId, operationId);
    }
}
