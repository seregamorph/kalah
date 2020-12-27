package com.backbase.kalah.config;

import com.backbase.kalah.filters.LogRequestFilter;
import lombok.val;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<LogRequestFilter> loggingFilter() {
        val filter = new LogRequestFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludeHeaders(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(1024);
        val bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(10);
        return bean;
    }

}
