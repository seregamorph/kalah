package com.backbase.kalah.utils;

import lombok.experimental.UtilityClass;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Nonnull;
import java.util.Arrays;

@UtilityClass
public class RequestMappingUtils {

    @Nonnull
    public static String getControllerMapping(Class<?> controllerClass) {
        RequestMapping mapping = AnnotatedElementUtils.findMergedAnnotation(controllerClass, RequestMapping.class);
        Assert.notNull(mapping, "Controller " + controllerClass.getName() + " does not have @RequestMapping");
        Assert.isTrue(mapping.value().length == 1, "Illegal paths "
                + Arrays.toString(mapping.value()) + ", should be single sized");
        return mapping.value()[0];
    }
}
