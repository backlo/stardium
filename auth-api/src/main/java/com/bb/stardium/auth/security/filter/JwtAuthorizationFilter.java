package com.bb.stardium.auth.security.filter;

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

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_EMAIL = "AuthorizeEmail";

    private final SecurityService securityService;

    public JwtAuthorizationFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtAuthorizationFilter : 시작");
        try {
            if (!isCreatePlayerUriAndMethod(request)) {
                checkPlayerReadAndUpdateUri(request);

                String authorizationHeader = ValidateAuthorizationHeader(request);

                String token = authorizationHeader.substring(7);
                String email = securityService.extractEmail(token);

                checkEmailAndSecurityContextAndExpiredToken(request, token, email);
            }
            super.doFilter(request, response, filterChain);
        } catch (AuthenticationServiceException | IllegalAccessException e) {
            exceptionHandling(response, e);
        }
    }

    private boolean isCreatePlayerUriAndMethod(HttpServletRequest request) {
        return request.getRequestURI().equals("/players") && request.getMethod().equals("POST");
    }

    private void checkPlayerReadAndUpdateUri(HttpServletRequest request) {
        if (!request.getRequestURI().startsWith("/players/")) {
            throw new AuthenticationServiceException("Authorization uri not supported : " + request.getRequestURI());
        }
    }

    private String ValidateAuthorizationHeader(HttpServletRequest request) {
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

    private void exceptionHandling(HttpServletResponse response, Exception e) throws IOException {
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
}
