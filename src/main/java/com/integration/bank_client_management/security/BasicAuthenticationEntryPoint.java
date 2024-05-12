package com.integration.bank_client_management.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.bank_client_management.constants.Constants;
import com.integration.bank_client_management.errors.ExceptionDetail;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class BasicAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        ExceptionDetail exceptionDetail = new ExceptionDetail(HttpStatus.UNAUTHORIZED.value(), new DateTime().toString(), Constants.ERROR_UNAUTHORIZED, request.getRequestURI());
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.write(mapper.writeValueAsString(exceptionDetail));
        writer.flush();
    }
}