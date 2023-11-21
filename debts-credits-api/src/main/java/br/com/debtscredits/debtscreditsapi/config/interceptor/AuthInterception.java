package br.com.debtscredits.debtscreditsapi.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import br.com.debtscredits.debtscreditsapi.modules.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class AuthInterception implements HandlerInterceptor{

    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(
    HttpServletRequest request, 
    HttpServletResponse response, 
    Object handler)
    throws Exception {
        if(HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            if (isOption(request))
            return true;
        }
        var authorization = request.getHeader(AUTHORIZATION);
        jwtService.validateAuthorization(authorization);
        return true;
    }

    private boolean isOption(HttpServletRequest request) {
        return HttpMethod.OPTIONS.name().equals(request.getMethod());
    }
    
}
