package com.clientside.mbkm.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.clientside.mbkm.Models.dto.request.LoginRequest;
import com.clientside.mbkm.Models.dto.response.LoginResponse;
import com.clientside.mbkm.utils.AuthSessionUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private RestTemplate restTemplate;

    public Boolean login(LoginRequest loginRequest) {
        try {
            ResponseEntity<LoginResponse> res = restTemplate.exchange(
                    "http://localhost:8080/auth/login",
                    HttpMethod.POST,
                    new HttpEntity<>(loginRequest),
                    LoginResponse.class);

            if (res.getStatusCode() == HttpStatus.OK) {
                setPrinciple(res.getBody(), loginRequest.getPassword());
                Authentication auth = AuthSessionUtil.getAuthentication();

                log.info("Name : {}", auth.getName());

                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public void setPrinciple(LoginResponse response, String password) {
        List<SimpleGrantedAuthority> authorities = response
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                response.getUsername(),
                password,
                authorities);

        // todo: set principle
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}