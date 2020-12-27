package com.backbase.kalah.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class LogRequestFilter extends AbstractRequestLoggingFilter {

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        log.info(message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
    }
}
