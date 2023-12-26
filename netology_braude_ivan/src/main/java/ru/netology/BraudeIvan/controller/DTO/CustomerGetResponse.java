package ru.netology.BraudeIvan.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerGetResponse {
    private final List<CustomerDTO> clients;
}
