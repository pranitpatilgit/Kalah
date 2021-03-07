package com.pranitpatil.kalah.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        //Add unique request id for each request to display in logs
        MDC.put("requestID", String.valueOf(System.currentTimeMillis()));
        chain.doFilter(request, response);
    }
}
