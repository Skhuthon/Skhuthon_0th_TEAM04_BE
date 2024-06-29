package com.example.sharediary.member.infrastructure;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthorizationHandlerInterceptor implements HandlerInterceptor {
    private final TokenProvider tokenProvider;

    public AuthorizationHandlerInterceptor(final TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true; // OPTIONS 요청 무시
        }
        final String token = tokenProvider.getTokenFromCookies(request);

        tokenProvider.validateTokenExpiration(token);
        tokenProvider.validateTokenRole(token);

        return true;
    }
}
