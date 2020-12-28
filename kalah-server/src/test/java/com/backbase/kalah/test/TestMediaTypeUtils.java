package com.backbase.kalah.test;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

@UtilityClass
public class TestMediaTypeUtils {

    private static final List<MediaType> PRINT_MEDIA_TYPES = Collections.unmodifiableList(Arrays.asList(
            MediaType.parseMediaType("text/*"),
            APPLICATION_JSON,
            APPLICATION_XML,
            MediaType.parseMediaType("application/hal+json")
    ));

    public static boolean isPrintable(@Nullable String contentType, String content) {
        if (contentType == null) {
            return StringUtils.isAsciiPrintable(content);
        }
        val mediaType = MediaType.parseMediaType(contentType);
        for (MediaType skippedMediaType : PRINT_MEDIA_TYPES) {
            if (skippedMediaType.includes(mediaType)) {
                return true;
            }
        }
        return false;
    }

}
