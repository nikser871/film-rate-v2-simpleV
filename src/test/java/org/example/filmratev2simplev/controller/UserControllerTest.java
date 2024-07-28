package org.example.filmratev2simplev.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.filmratev2simplev.config.AppConfig;
import org.example.filmratev2simplev.config.AppProperties;
import org.example.filmratev2simplev.dto.UserDTO;
import org.example.filmratev2simplev.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @MockBean
    AppProperties appProperties;



    @Test
    void testCreateUser() throws Exception {
        UserDTO user = UserDTO.builder()
                .id(null)
                .name("Maxim")
                .email("max@mail.ru")
                .login("max2001")
                .birthday(LocalDate.of(2001, 12, 12))
                .build();

        UserDTO user2 = UserDTO.builder()
                .id(1L)
                .name("Maxim")
                .email("max@mail.ru")
                .login("max2001")
                .birthday(LocalDate.of(2001, 12, 12))
                .build();


        given(userService.createUser(any(UserDTO.class))).willReturn(Optional.of(user2));

        mockMvc.perform(post("http://localhost:8080/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

    }

}
