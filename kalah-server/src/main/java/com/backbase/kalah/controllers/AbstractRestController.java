package com.backbase.kalah.controllers;

import com.backbase.kalah.utils.RequestMappingUtils;
import lombok.val;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public abstract class AbstractRestController {

    protected URI location(String pathTemplate, Object... pathVariables) {
        val request = getCurrentRequest();
        // support "x-forwarded-host" and "x-forwarded-proto" for reverse-proxy
        return ServletUriComponentsBuilder.fromServletMapping(request)
                .path(RequestMappingUtils.getControllerMapping(getClass()) + pathTemplate)
                .buildAndExpand(pathVariables)
                .toUri();
    }

    @Nonnull
    private static HttpServletRequest getCurrentRequest() {
        val requestAttributes = RequestContextHolder.currentRequestAttributes();
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

}
