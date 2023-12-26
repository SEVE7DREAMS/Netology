package ru.netology.BraudeIvan.service;

import org.springframework.stereotype.Service;
import ru.netology.BraudeIvan.domain.Customer;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final List<Customer> customerList = new ArrayList<>();

    public void addCustomer(int id, String name) {
        Customer customer = new Customer(id, name);
        customerList.add(customer);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customerList);
    }

    public void removeCustomer(int id) {
        customerList.removeIf(c -> c.getId() == id);
    }

    @PostConstruct
    public void initialize() {
        customerList.add(new Customer(1, "Spring"));
        customerList.add(new Customer(2, "Boot"));
    }
}
