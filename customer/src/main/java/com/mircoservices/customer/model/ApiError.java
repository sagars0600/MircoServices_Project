package com.mircoservices.customer.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiError {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private List<String> errors;
    private String path;
}
