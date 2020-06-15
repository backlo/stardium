package com.bb.stardium.auth.security.filter;

import com.bb.stardium.auth.security.filter.dto.LoginViewModel;
import com.bb.stardium.auth.security.filter.dto.core.PlayerViewModel;
import com.bb.stardium.util.CopyHttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("Authentication Filter : authenticate 시작!");
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported : " + request.getMethod());
        }

        try {
            HttpServletRequest copyRequest = new CopyHttpServletRequest(request);
            PlayerViewModel credentials = new ObjectMapper()
                    .readValue(copyRequest.getInputStream(), LoginViewModel.class);

            String username = StringUtils.isEmpty(credentials.getEmail()) ? "" : credentials.getEmail().trim();
            String password = StringUtils.isEmpty(credentials.getPassword()) ? "" : credentials.getPassword();

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(copyRequest, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (IOException e) {
            throw new AuthenticationServiceException("Can not read request Content");
        }
    }
}