package io.recruitment.assessment.api.security.JwtAuthentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.recruitment.assessment.api.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.header}")
    private String requestHeader;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long validPeriodInMilliseconds;

    @Value("${jwt.prefix}")
    private String prefix;

    private final UserDetailsService userDetailsService;

    public String createToken(String username) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("HardwareStore")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validPeriodInMilliseconds))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        readToken(token);
        return true;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return readToken(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        if (request.getHeader(requestHeader) == null) {
            return null;
        }
        return request.getHeader(requestHeader).replaceAll(prefix, "");
    }

    private Jws<Claims> readToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return jws;
        } catch (JwtException exception) {
            throw new ApiRequestException("JWT token is expired or invalid.", HttpStatus.UNAUTHORIZED);
        }
    }
}
