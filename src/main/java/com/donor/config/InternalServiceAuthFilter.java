//package com.donor.config;
//
//import java.io.IOException;
//
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class InternalServiceAuthFilter extends OncePerRequestFilter {
//
//    private final String expectedToken;
//
//    public InternalServiceAuthFilter(Environment env) {
//        this.expectedToken = env.getProperty("internal.service-token", "");
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
//            throws ServletException, IOException {
//        String path = req.getRequestURI();
//        // Apply only to internal endpoints
//        if (path.startsWith("/donor/internal/")) {
//            String token = req.getHeader("X-Service-Token");
//            if (token == null || !token.equals(expectedToken)) {
//                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                resp.getWriter().write("Unauthorized");
//                return;
//            }
//        }
//        chain.doFilter(req, resp);
//    }
//}
