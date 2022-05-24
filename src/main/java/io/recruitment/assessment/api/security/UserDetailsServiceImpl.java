package io.recruitment.assessment.api.security;

import io.recruitment.assessment.api.exception.ApiRequestException;
import io.recruitment.assessment.api.model.User;
import io.recruitment.assessment.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s)
                .orElseThrow(() -> new ApiRequestException("User not found.", HttpStatus.NOT_FOUND));
        return new UserPrincipal(user.getId(), user.getEmail(), user.getPassword(), user.getRoles());
    }
}
