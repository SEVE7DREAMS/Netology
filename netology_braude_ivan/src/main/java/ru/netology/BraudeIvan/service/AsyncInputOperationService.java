package ru.netology.BraudeIvan.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.netology.BraudeIvan.configuration.OperationProperties;
import ru.netology.BraudeIvan.domain.operation.Operation;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Queue;

@Component
@RequiredArgsConstructor
@Setter
public class AsyncInputOperationService {

    private final Queue<Operation> operationQueue = new LinkedList<>();
    private final StatementService statementService;
    private final OperationProperties operationProperties;

    public boolean addOperation(Operation operation) {
        System.out.println("Operation added for processing " + operation);
        return operationQueue.offer(operation);
    }

    public void startProcessing() {
        Thread processingThread = new Thread(this::processQueue);
        processingThread.start();
    }

    private void processQueue() {
        while (true) {
            Operation operation = operationQueue.poll();
            if (operation == null) {
                try {
                    System.out.println("No operations");
                    Thread.sleep(operationProperties.getTimeout());
                } catch (InterruptedException e) {
                    System.out.println(e);
                    Thread.currentThread().interrupt();
                }
            } else {
                System.out.println("Processing operation " + operation);
                processOperation(operation);
            }
        }
    }

    private void processOperation(Operation operation) {
        statementService.saveOperation(operation);
    }

    @PostConstruct
    public void init() {
        this.startProcessing();
    }
}
