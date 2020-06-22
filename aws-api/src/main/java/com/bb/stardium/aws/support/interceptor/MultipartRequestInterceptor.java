package com.bb.stardium.aws.support.interceptor;

import com.bb.stardium.error.exception.NotAllowImageFileException;
import com.bb.stardium.util.CopyHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MultipartRequestInterceptor extends HandlerInterceptorAdapter {
    private static final int MULTIPART_FILE_CONTENT_LENGTH_MINIMUM_SIZE = 100;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpServletRequest copyRequest = new CopyHttpServletRequest(request);

        if (copyRequest.getContentLength() < MULTIPART_FILE_CONTENT_LENGTH_MINIMUM_SIZE) {
            throw new NotAllowImageFileException();
        }

        return super.preHandle(request, response, handler);
    }
}
