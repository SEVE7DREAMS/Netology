package ru.netology.BraudeIvan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.BraudeIvan.controller.DTO.CustomerDTO;
import ru.netology.BraudeIvan.controller.DTO.CustomerGetResponse;
import ru.netology.BraudeIvan.domain.Customer;
import ru.netology.BraudeIvan.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public CustomerGetResponse getClients() {
        List<Customer> customers = customerService.getCustomers();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customer -> new CustomerDTO(customer.getId(), customer.getName()))
                .collect(Collectors.toList());

        return new CustomerGetResponse(customerDTOS);
    }

    @GetMapping("{customerId}")
    public CustomerDTO getCustomer(@PathVariable int customerId) {
        return customerService.getCustomers().stream()
                .filter(c -> c.getId() == customerId)
                .map(c -> new CustomerDTO(c.getId(), c.getName()))
                .findFirst().orElse(null);
    }

    @PostMapping("add")
    public ResponseEntity<Object> addCustomer(@RequestParam int customerId, @RequestParam String name) {
        customerService.addCustomer(customerId,name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Object> removeCustomer(@PathVariable int customerId) {
        customerService.removeCustomer(customerId);
        return ResponseEntity.ok().build();
    }
}
