package ru.netology.BraudeIvan.configuration;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "operation.processing")
public class OperationProperties {
    private int timeout;
}
