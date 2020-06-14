package com.bb.stardium.security.config.filter;

import com.bb.stardium.error.model.ErrorResponse;
import com.bb.stardium.security.service.SecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class JwtVerifyFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtVerifyFilter.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_EMAIL = "AuthorizeEmail";

    private final SecurityService securityService;
    private final Map<String, String> ignoreRequest;

    public JwtVerifyFilter(SecurityService securityService, Map<String, String> ignoreRequest) {
        this.securityService = securityService;
        this.ignoreRequest = ignoreRequest;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Jwt Verify Filter : 시작");
        try {
            String authorizationHeader = validateAuthorizationHeader(request);

            String token = authorizationHeader.substring(7);
            String email = securityService.extractEmail(token);

            checkEmailAndSecurityContextAndExpiredToken(request, token, email);
            super.doFilter(request, response, filterChain);
        } catch (IllegalAccessException e) {
            exceptionHandling(response, e);
        }
    }

    private String validateAuthorizationHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (authorizationHeader == null || authorizationHeader.startsWith("Bearer ")) {
            throw new AuthenticationServiceException("Invalid Jwt Token : Can't Accessed");
        }

        return authorizationHeader;
    }

    private void checkEmailAndSecurityContextAndExpiredToken(HttpServletRequest request, String token, String email) throws IllegalAccessException {
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = securityService.loadUserByUsername(email);
            if (!securityService.isTokenExpired(token)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                request.setAttribute(AUTHORIZATION_EMAIL, email);
            }
        }
    }

    private void exceptionHandling(HttpServletResponse response, IllegalAccessException e) throws IOException {
        log.info("JwtException : {}", e.getMessage());
        String responseError = new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(
                        new ErrorResponse(HttpStatus.FORBIDDEN, e)
                );

        response.setContentType("application/json");
        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

        response.getWriter().write(responseError);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return ignoreRequest.entrySet().stream()
                .anyMatch(map ->
                        map.getKey().equals(request.getRequestURI()) && map.getValue().equals(request.getMethod())
                );
    }
}
