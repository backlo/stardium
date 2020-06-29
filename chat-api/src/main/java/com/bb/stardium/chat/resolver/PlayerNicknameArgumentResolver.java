package com.bb.stardium.chat.resolver;

import com.bb.stardium.security.service.SecurityService;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class PlayerNicknameArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final SecurityService securityService;

    public PlayerNicknameArgumentResolver(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PlayerNickname.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER).substring(7);

        return securityService.extractNickname(authorizationHeader);
    }
}
