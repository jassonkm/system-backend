package com.system.permission.systembackend.web.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private Long id;

    private String email;
    private String type = "Bearer";
    private String token;
    private List<String> roles;

    public JwtResponse(Long id, String email, String accessToken, List<String> roles) {
        this.id = id;
        this.email = email;
        this.token = accessToken;
        this.roles = roles;
    }
}
