package io.recruitment.assessment.api.controller;

import io.recruitment.assessment.api.dto.authentication.AuthenticationResponse;
import io.recruitment.assessment.api.dto.authentication.LoginRequest;
import io.recruitment.assessment.api.dto.authentication.RegisterRequest;
import io.recruitment.assessment.api.mapper.AuthenticationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationMapper authenticationMapper;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        AuthenticationResponse response = authenticationMapper.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        AuthenticationResponse response = authenticationMapper.login(request);
        return ResponseEntity.ok(response);
    }
}
