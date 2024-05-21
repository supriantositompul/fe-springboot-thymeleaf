package com.clientside.mbkm.Controllers.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clientside.mbkm.Models.Employee;
import com.clientside.mbkm.Models.Role;
import com.clientside.mbkm.Models.dto.request.ChangePassword;
import com.clientside.mbkm.Services.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employee")
public class RestEmployeeController {

    private EmployeeService employeeService;

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return employeeService.getRoles();
    }

    @PostMapping
    public ResponseEntity<String> updatePassword(@RequestBody ChangePassword request) {
        return ResponseEntity.ok(employeeService.updatePassword(request));
    }

    @PutMapping
    public ResponseEntity<Employee> updatePhone(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updatePhone(employee));
    }
}