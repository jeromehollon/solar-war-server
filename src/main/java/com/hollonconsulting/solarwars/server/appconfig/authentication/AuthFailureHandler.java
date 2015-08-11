package com.hollonconsulting.solarwars.server.appconfig.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response,
                                        AuthenticationException exception) throws ServletException, IOException  {


        response.setContentType("application/vnd.captech-v1.0+json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter out = response.getWriter();
        out.print("{\"message\":\"Full authentication is required to access this resource.\", \"access-denied\":true,\"cause\":\"NOT AUTHENTICATED\"}");
        out.flush();
        out.close();

        //super.onAuthenticationFailure(request, response, exception);
    }
}