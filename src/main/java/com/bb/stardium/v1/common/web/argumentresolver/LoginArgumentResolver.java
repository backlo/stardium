package com.bb.stardium.v1.common.web.argumentresolver;

import com.bb.stardium.v1.common.web.annotation.LoggedInPlayer;
import com.bb.stardium.v1.player.dto.PlayerSessionDto;
import com.bb.stardium.v1.player.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger log = LoggerFactory.getLogger(LoginArgumentResolver.class);

    private final PlayerService playerService;

    public LoginArgumentResolver(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoggedInPlayer.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter,
                                  final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest,
                                  final WebDataBinderFactory binderFactory) {
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        final PlayerSessionDto sessionDto = (PlayerSessionDto) request.getSession().getAttribute("login");

        return playerService
                .findById(sessionDto.getPlayerId());
    }

}
