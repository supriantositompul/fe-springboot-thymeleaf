package com.clientside.mbkm.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.clientside.mbkm.Models.Employee;
import com.clientside.mbkm.Models.Role;
import com.clientside.mbkm.Models.dto.request.ChangePassword;

@Service
public class EmployeeService {

    @Autowired
    private RestTemplate restTemplate;
    private RestTemplate getRoles = new RestTemplate();

    public Employee getByName(String name) {
        return restTemplate.exchange(
                "http://localhost:8080/auth/password" + "/name/" + name,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Employee>() {
                }).getBody();
    }

    public String updatePassword(ChangePassword request) {
        return restTemplate.exchange(
                "http://localhost:8080/auth/password",
                HttpMethod.POST,
                new HttpEntity<ChangePassword>(request),
                new ParameterizedTypeReference<String>() {
                }).getBody();
    }

    public Employee updatePhone(Employee request) {
        return restTemplate.exchange(
                "http://localhost:8080/auth/phone",
                HttpMethod.POST,
                new HttpEntity<Employee>(request),
                new ParameterizedTypeReference<Employee>() {
                }).getBody();
    }

    public List<Role> getRoles() {
        return getRoles.exchange(
                "http://localhost:8080/role",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Role>>() {
                }).getBody();
    }
    }