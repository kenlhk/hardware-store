package io.recruitment.assessment.api.service;

import io.recruitment.assessment.api.model.User;

import java.util.Map;

public interface AuthenticationService {

    Map<String, String> register(String email, String password);

    Map<String, String> login(String email, String password);

    User getCurrentUser();
}
