package com.backbase.kalah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class AbstractRestControllerMockMvcIT {

    @Autowired
    protected MockMvc mockMvc;

}
