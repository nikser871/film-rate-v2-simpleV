package org.example.filmratev2simplev.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.filmratev2simplev.config.AppConfig;
import org.example.filmratev2simplev.config.AppProperties;
import org.example.filmratev2simplev.mappers.UserMapper;
import org.example.filmratev2simplev.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.core.Is.is;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@Import(AppConfig.class)
public class UserControllerIT {

    @Autowired
    UserController userController;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AppConfig appConfig;

    @Autowired
    AppProperties appProperties;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        appProperties = appConfig.appProperties();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void getAllUsers() throws Exception {
        mockMvc.perform(get("http://localhost:8080/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(3)));

    }

}
