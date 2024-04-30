package com.pnk.cbresiliencedemo.controller;

import com.pnk.cbresiliencedemo.service.DemoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;


@ContextConfiguration(classes = {DemoRestController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DemoRestControllerTest {

    @Autowired
    private DemoRestController demoRestController;

    @MockBean
    private DemoService demoService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void testSend() throws Exception {
        // Arrange
        doNothing().when(demoService).send(any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(demoRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testSend2() throws Exception {
        // Arrange
        doNothing().when(demoService).send(any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(demoRestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}