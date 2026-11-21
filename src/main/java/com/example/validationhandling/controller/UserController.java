package com.example.validationhandling.controller;
import com.example.validationhandling.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody UserRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "User data is valid and received successfully");
        response.put("name", request.getName());
        response.put("email", request.getEmail());
        response.put("age", request.getAge());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
