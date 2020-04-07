package com.bb.stardium.common.web.interceptor;

import com.bb.stardium.common.web.service.SessionService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    private static final String LOGIN_PATH = "/login";

    private final SessionService sessionService;

    public AuthenticationInterceptor(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) throws Exception {
        if (sessionService.isLoggedIn(request.getSession())) {
            return true;
        }
        response.sendRedirect(LOGIN_PATH);
        return false;
    }

}
