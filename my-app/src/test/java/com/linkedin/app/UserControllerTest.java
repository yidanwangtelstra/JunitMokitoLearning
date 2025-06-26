package com.linkedin.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
// the UserController also has the UserService dependency, so we need to mock it using mockito
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // use @MockBean instead of @Mock to replace the UserService in a Spring application context
    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private final User USER = new User("Jane Doe", "jane.doe@example.com");
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createUserTest() throws Exception {
        // stub
        when(userService.saveUser(any(User.class))).thenReturn(USER);

        // make a post request on the mockMvc object
        MvcResult mvcResult = mockMvc.perform(post("/users")     .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Jane Doe\", \"email\":\"jane.doe@example.com\"}"))
                .andExpect(status().isOk())
                .andReturn();

        // the first way to access the returned object it to access the mvc object and convert it to a Java object using an object mapper

        // then we will retrieve the string result and use Jackson ObjectMapper to transform it into a User object
        User result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);

        // it's important to test each field of the User object because Jackson creates a new object and does not return the same reference
        assertEquals(USER.getName(), result.getName());
        assertEquals(USER.getEmail(), result.getEmail());
        assertEquals(USER.getId(), result.getId());
        verify(userService).saveUser(any(User.class));
    }


    @Test
    public void getUserByIdTest() throws Exception {
        when(userService.getUserById(anyLong())).thenReturn(Optional.of(USER));

        // The second way to access the returned object is to use the chain operations within andExcpect method
        mockMvc.perform(get("/users/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value(USER.getName()))
                .andExpect(jsonPath("$.email").value(USER.getEmail()));
        verify(userService).getUserById(anyLong());
        ;
    }

    @Test
    public void getUserByIdNotFoundTest() throws Exception {
        mockMvc.perform(get("/users/2"))
                .andExpect(status().isNotFound());
        verify(userService).getUserById(anyLong());
    }
}
