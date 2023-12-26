package ru.netology.BraudeIvan.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class OperationDTO {
    private final int id;
    private final int customerId;
    private int sum;
    private String currency;
    private String merchant;
}

