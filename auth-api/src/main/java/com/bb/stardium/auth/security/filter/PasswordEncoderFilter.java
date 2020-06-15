package com.bb.stardium.auth.security.filter;

import com.bb.stardium.auth.security.filter.dto.PlayerCreateViewModel;
import com.bb.stardium.auth.security.filter.dto.PlayerEditViewModel;
import com.bb.stardium.auth.security.filter.dto.PlayerViewModel;
import com.bb.stardium.error.exception.FieldsEmptyException;
import com.bb.stardium.error.model.ErrorResponse;
import com.bb.stardium.util.CopyHttpServletRequest;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@WebFilter("/players")
public class PasswordEncoderFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(PasswordEncoderFilter.class);
    private final PasswordEncoder passwordEncoder;
    private final Map<String, String> includeRequest;

    public PasswordEncoderFilter(PasswordEncoder passwordEncoder, Map<String, String> includeRequest) {
        this.passwordEncoder = passwordEncoder;
        this.includeRequest = includeRequest;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException {
        log.info("PasswordEncoderFilter : 시작");
        try {
            HttpServletRequest copyRequest = new CopyHttpServletRequest(request);
            PlayerViewModel playerCreateViewModel = checkingRequest(copyRequest);

            String encodePassword = passwordEncoder.encode(playerCreateViewModel.getPassword());

            request.setAttribute("encodedPassword", encodePassword);

            chain.doFilter(copyRequest, response);

        } catch (FieldsEmptyException | ServletException | UnrecognizedPropertyException exception) {
            exceptionHandler(response, exception);
        }
    }

    private PlayerViewModel checkingRequest(HttpServletRequest copyRequest) throws IOException {
        return copyRequest.getMethod().equals("POST") ?
                new ObjectMapper().readValue(copyRequest.getInputStream(), PlayerCreateViewModel.class).checkNullField() :
                new ObjectMapper().readValue(copyRequest.getInputStream(), PlayerEditViewModel.class);

    }

    private void exceptionHandler(HttpServletResponse response, Exception exception) throws IOException {
        String responseError = new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(
                        new ErrorResponse(HttpStatus.FORBIDDEN, exception)
                );

        response.setContentType("application/json");
        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

        response.getWriter().write(responseError);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return includeRequest.entrySet().stream()
                .noneMatch(map ->
                        map.getKey().equals(request.getRequestURI()) && map.getValue().equals(request.getMethod())
                );
    }
}
