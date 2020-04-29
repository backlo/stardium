package com.bb.stardium.v1.player.web.advice;

import com.bb.stardium.v1.common.web.argumentresolver.Redirection;
import com.bb.stardium.v1.player.service.exception.AuthenticationFailException;
import com.bb.stardium.v1.player.service.exception.EmailNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class LoginControllerAdvice {

    @ExceptionHandler(AuthenticationFailException.class)
    public RedirectView handleAuthenticationFailException(AuthenticationFailException e, RedirectAttributes redirectAttributes, Redirection redirection) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return new RedirectView(redirection.getRedirectUrl());
    }

    @ExceptionHandler(EmailNotExistException.class)
    public RedirectView handleEmailNotExistException(EmailNotExistException e, RedirectAttributes redirectAttributes, Redirection redirection) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return new RedirectView(redirection.getRedirectUrl());
    }
}
