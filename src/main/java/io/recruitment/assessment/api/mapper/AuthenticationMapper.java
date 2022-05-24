package io.recruitment.assessment.api.mapper;

import io.recruitment.assessment.api.dto.authentication.AuthenticationResponse;
import io.recruitment.assessment.api.dto.authentication.LoginRequest;
import io.recruitment.assessment.api.dto.authentication.RegisterRequest;
import io.recruitment.assessment.api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationMapper {

    private final MapStructMapper mapper;
    private final AuthenticationService authenticationService;

    public AuthenticationResponse register(RegisterRequest request) {
        Map<String, String> map = authenticationService.register(
                request.getEmail(),
                request.getPassword());
        return mapper.toAuthDto(map);
    }

    public AuthenticationResponse login(LoginRequest request) {
        Map<String, String> map = authenticationService.login(
                request.getEmail(),
                request.getPassword());
        return mapper.toAuthDto(map);
    }
}
