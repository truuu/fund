package fund.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationResultHandler implements AuthenticationFailureHandler, AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
            throws IOException, ServletException {
        String url = "/home/login.do?";
        if (ex instanceof LockedException) url += "locked";
        else if (ex instanceof BadCredentialsException) url += "count=" + ex.getMessage();
        else url += "error";
        response.sendRedirect(request.getContextPath() + url);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication arg2)
            throws IOException, ServletException {
        response.sendRedirect(request.getContextPath());
    }
}
