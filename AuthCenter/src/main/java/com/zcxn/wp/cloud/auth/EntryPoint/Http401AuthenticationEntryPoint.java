package com.zcxn.wp.cloud.auth.EntryPoint;

/**
 * @author: KMAN
 * @exception:
 * @version: 1.0
 * @description: update_version: update_date: update_author: update_note:
 */

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class Http401AuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final String headerValue;

    public Http401AuthenticationEntryPoint(String headerValue) {
        this.headerValue = headerValue;
    }

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("WWW-Authenticate", this.headerValue);
        response.sendError(401, authException.getMessage());
    }
}

