package com.bb.stardium.resolver;

import com.bb.stardium.resolver.annotation.AuthorizePlayer;
import com.bb.stardium.chat.service.player.PlayerService;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class AuthorizePlayerArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String AUTHORIZATION_EMAIL = "AuthorizeEmail";

    private final PlayerService playerService;

    public AuthorizePlayerArgumentResolver(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthorizePlayer.class);
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
