package com.bb.stardium.resource.security.filter;

import com.bb.stardium.error.model.ErrorResponse;
import com.bb.stardium.security.service.SecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import java.util.List;

public class JwtTokenVerifyFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenVerifyFilter.class);

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_EMAIL = "Authorize_Email";

    private final SecurityService securityService;

    public JwtTokenVerifyFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("JwtTokenVerifyFilter : 시작");
        try {
            String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

            String token = authorizationHeader.substring(7);
            String email = securityService.extractSubject(token);
            String authorities = securityService.extractAuthorities(token);

            if (!securityService.isTokenExpired(token)) {
                UserDetails userDetails = securityService.loadUserByUsername(email);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        List.of(new SimpleGrantedAuthority(authorities))
                );

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                request.setAttribute(AUTHORIZATION_EMAIL, email);

                super.doFilter(request, response, chain);
            }
        } catch (IllegalAccessException e) {
            exceptionHandling(response, e);
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
}
