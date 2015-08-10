package com.hollonconsulting.solarwars.server.appconfig.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotAuthorizedException;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    @Override
    public void commence( HttpServletRequest request, HttpServletResponse response,
                          AuthenticationException authException ) throws IOException {
        response.setContentType("application/vnd.captech-v1.0+json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter out = response.getWriter();
        out.print("{\"message\":\"Full authentication is required to access this resource.\", \"access-denied\":true,\"cause\":\"NOT AUTHENTICATED\"}");
        out.flush();
        out.close();

        //throw new NotAuthorizedException("Authentication is required to access this resource.");
    }
}
