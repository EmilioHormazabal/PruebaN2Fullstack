package com.duoc.hospital.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component
//public class SecurityApiKeyFilter extends OncePerRequestFilter {

    //private static final String API_KEY = "Duocadmin25"; // Cambia por una clave segura

   // @Override
    //protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            //throws ServletException, IOException {
        //String apiKey = request.getHeader("X-API-KEY");
        //if (API_KEY.equals(apiKey)) {
            //filterChain.doFilter(request, response);
        //} else {
            //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            //response.getWriter().write("Unauthorized: API Key missing or invalid");
        //}
    //}
//}