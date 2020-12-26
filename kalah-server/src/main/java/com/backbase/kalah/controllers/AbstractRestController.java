package com.backbase.kalah.controllers;

import com.backbase.kalah.utils.RequestMappingUtils;
import lombok.val;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.net.URI;

import static com.backbase.kalah.controllers.ControllerConstants.ENDPOINT_ID;

public abstract class AbstractRestController {

    protected URI locationById(Serializable id) {
        Assert.isTrue(id != null, "id is null");

        return location(ENDPOINT_ID, id);
    }

    private URI location(String pathTemplate, Object... pathVariables) {
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
