package com.bb.stardium.util;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class CopyHttpServletRequest extends HttpServletRequestWrapper {
    private final ByteArrayOutputStream cachedBytes;

    public CopyHttpServletRequest(HttpServletRequest request) {
        super(request);
        cachedBytes = new ByteArrayOutputStream();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (cachedBytes.size() < 1) {
            IOUtils.copy(super.getInputStream(), cachedBytes);
        }

        return new CachedServletInputStream();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public class CachedServletInputStream extends ServletInputStream {
        private final ByteArrayInputStream input;

        public CachedServletInputStream() {
            input = new ByteArrayInputStream(cachedBytes.toByteArray());
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener listener) {

        }

        @Override
        public int read() {
            return input.read();
        }
    }
}

