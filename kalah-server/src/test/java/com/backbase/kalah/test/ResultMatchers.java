package com.backbase.kalah.test;

import lombok.experimental.UtilityClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.annotation.Nullable;
import java.util.Collection;

@UtilityClass
public class ResultMatchers {

    public static ResultMatcher collect(@Nullable Collection<? extends ResultMatcher> resultMatchers) {
        if (resultMatchers == null) {
            return success();
        }

        if (resultMatchers.size() == 1) {
            return resultMatchers.iterator().next();
        }

        return mvcResult -> Assertions.assertAll(resultMatchers.stream()
                .map(resultMatcher -> (Executable) () -> resultMatcher.match(mvcResult)));
    }

    private static ResultMatcher success() {
        return mvcResult -> {
        };
    }

}
