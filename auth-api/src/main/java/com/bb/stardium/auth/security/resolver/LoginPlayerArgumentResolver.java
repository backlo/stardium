package com.bb.stardium.auth.security.resolver;

import com.bb.stardium.auth.security.annotation.LoginPlayer;
import com.bb.stardium.service.player.PlayerService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginPlayerArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String AUTHORIZATION_EMAIL = "AuthorizeEmail";

    private final PlayerService playerService;

    public LoginPlayerArgumentResolver(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginPlayer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String email = (String) request.getAttribute(AUTHORIZATION_EMAIL);

        return playerService.findPlayerByEmail(email);
    }
}
