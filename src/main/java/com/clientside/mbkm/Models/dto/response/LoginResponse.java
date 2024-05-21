package com.clientside.mbkm.Models.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponse {
    private String username;
    private String password;
    private List<String> authorities;
}
