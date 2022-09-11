package com.windula.oms.filter;

import com.windula.oms.common.Constants.CorsFilterConst;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader(CorsFilterConst.HEADER_NAME_ALLOW_ORIGIN, CorsFilterConst.VALUE_ALLOW_ORIGIN);
        response.setHeader(CorsFilterConst.HEADER_NAME_ALLOW_METHODS, CorsFilterConst.VALUE_ALLOW_METHODS);
        response.setHeader(CorsFilterConst.HEADER_NAME_MAX_AGE, CorsFilterConst.VALUE_MAX_AGE);
        response.setHeader(CorsFilterConst.HEADER_NAME_ALLOW_HEADERS, CorsFilterConst.VALUE_ALLOW_HEADERS);
        filterChain.doFilter(request, response);
    }
}
