package com.bb.stardium.v1.common.web.interceptor;

import com.bb.stardium.v1.common.web.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class UnAuthenticationInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(UnAuthenticationInterceptor.class);

    private static final String ROOT_PATH = "/";

    private final SessionService sessionService;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) throws Exception {
        log.info("unAuth 들어감");
        if (sessionService.isLoggedIn(request.getSession())) {
            response.sendRedirect(ROOT_PATH);
            return false;
        }
        return true;
    }

}
