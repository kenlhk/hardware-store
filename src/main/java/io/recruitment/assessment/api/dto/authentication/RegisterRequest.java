package io.recruitment.assessment.api.dto.authentication;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}
