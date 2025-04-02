package com.jhojan.springboot.crudjpa.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhojan.springboot.crudjpa.security.JWTConfig;
import com.jhojan.springboot.crudjpa.security.SimpleGrantedAuthorityJsonCreator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JWTValidationFilter extends BasicAuthenticationFilter {

    public JWTValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JWTConfig.HEADER_AUTHORIZATION);
        if (header == null || !header.startsWith(JWTConfig.PREFIX_TOKEN)) {
            chain.doFilter(request, response);
        }

        String token = header.replace(JWTConfig.PREFIX_TOKEN, "");

        try {
            Claims claims = Jwts.parser().verifyWith(JWTConfig.SECRET_KEY).build().parseSignedClaims(token).getPayload();
            String username = claims.getSubject();
            Object claimAuthorities = claims.get("authorities");

            Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                    new ObjectMapper()
                            .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                            .readValue(claimAuthorities.toString().getBytes(), SimpleGrantedAuthority[].class)
            );

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } catch (JwtException exception) {
            Map<String, String> body = new HashMap<>();
            body.put("error", exception.getMessage());
            body.put("message", "El token JWT es invalido!");
            body.put("serverMessage", exception.getMessage());

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

    }
}
