package com.bb.stardium.resource.security.handler;

import com.bb.stardium.resource.security.annotation.AuthorizePlayer;
import com.bb.stardium.service.player.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthorizationArgumentResolver implements HandlerMethodArgumentResolver {
    private static final Logger log = LoggerFactory.getLogger(AuthorizationArgumentResolver.class);
    private static final String AUTHORIZATION_SUBJECT = "Subject";

    private final PlayerService playerService;

    public AuthorizationArgumentResolver(PlayerService playerService) {
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
                                  WebDataBinderFactory binderFactory) {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        log.info("resolveArgument Email : {}", request.getAttribute(AUTHORIZATION_SUBJECT));
        Long id = Long.valueOf((String) request.getAttribute(AUTHORIZATION_SUBJECT));

        return playerService.findPlayerById(id);
    }

}