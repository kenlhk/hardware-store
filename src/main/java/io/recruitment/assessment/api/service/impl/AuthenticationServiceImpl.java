package io.recruitment.assessment.api.service.impl;

import io.recruitment.assessment.api.exception.ApiRequestException;
import io.recruitment.assessment.api.model.Cart;
import io.recruitment.assessment.api.model.Role;
import io.recruitment.assessment.api.model.User;
import io.recruitment.assessment.api.repository.CartRepository;
import io.recruitment.assessment.api.repository.UserRepository;
import io.recruitment.assessment.api.security.JwtAuthentication.JwtProvider;
import io.recruitment.assessment.api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> register(String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ApiRequestException("Email has been registered.", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.getRoles().add(Role.CUSTOMER); // default role

        String token = jwtProvider.createToken(email);
        Map<String, String> response = new HashMap<>();
        response.put("email", email);
        response.put("token", token);

        Cart cart = new Cart();
        cartRepository.save(cart);
        userRepository.save(user);
        return response;
    }

    @Override
    public Map<String, String> login(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (Exception e) {
            throw new ApiRequestException("Incorrect email or password.", HttpStatus.FORBIDDEN);
        }
        String token = jwtProvider.createToken(email);
        Map<String, String> response = new HashMap<>();
        response.put("email", email);
        response.put("token", token);
        return response;
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ApiRequestException("User not found.", HttpStatus.NOT_FOUND));
    }
}
