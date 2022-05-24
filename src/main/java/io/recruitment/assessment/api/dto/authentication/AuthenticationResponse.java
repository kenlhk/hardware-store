package io.recruitment.assessment.api.dto.authentication;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String email;
    private String token;
}
