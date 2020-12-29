package com.backbase.kalah.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;

/**
 * Super-class for integration testing. Note: subclasses will inherit rollback-only semantics (for MockMvc).
 */
@Rollback
@Transactional
@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class AbstractRestControllerMockMvcIT {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    protected <T> T readJson(String content, Class<T> targetType) {
        return objectMapper.readValue(content, targetType);
    }

}
