package com.bb.stardium.auth.security.filter;

import com.bb.stardium.auth.security.filter.dto.PlayerViewModel;
import com.bb.stardium.error.exception.FieldsEmptyException;
import com.bb.stardium.auth.security.wrapper.CopyHttpServletRequest;
import com.bb.stardium.error.model.ErrorResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter("/players")
public class PasswordEncoderFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(PasswordEncoderFilter.class);
    private final PasswordEncoder passwordEncoder;

    public PasswordEncoderFilter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException {
        log.info("PasswordEncoderFilter : 시작");
        try {
            HttpServletRequest copyRequest = new CopyHttpServletRequest(request);
            PlayerViewModel playerViewModel = new ObjectMapper()
                    .readValue(copyRequest.getInputStream(), PlayerViewModel.class);

            if (isEmptyOneOfFields(playerViewModel)) {
                throw new FieldsEmptyException();
            }

            String encodePassword = passwordEncoder.encode(playerViewModel.getPassword());

            request.setAttribute("encodedPassword", encodePassword);

            chain.doFilter(copyRequest, response);

        } catch (FieldsEmptyException | ServletException exception) {
            exceptionHandler(response, exception);
        }
    }

    private boolean isEmptyOneOfFields(PlayerViewModel playerViewModel) {
        return StringUtils.isEmpty(playerViewModel.getEmail()) ||
                StringUtils.isEmpty(playerViewModel.getPassword()) ||
                StringUtils.isEmpty(playerViewModel.getNickname());
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
        return !(request.getMethod().equals("POST") && request.getRequestURI().equals("/players"));
    }
}
