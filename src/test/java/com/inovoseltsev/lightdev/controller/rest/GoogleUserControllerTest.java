package com.inovoseltsev.lightdev.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inovoseltsev.lightdev.domain.entity.GoogleUser;
import com.inovoseltsev.lightdev.domain.userfinder.google.GoogleUserFinderByEmail;
import com.inovoseltsev.lightdev.domain.userfinder.google.GoogleUserFinderById;
import com.inovoseltsev.lightdev.service.GoogleUserService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class GoogleUserControllerTest {

    @MockBean
    private GoogleUserService googleUserService;

    @MockBean
    private GoogleUserFinderById finderById;

    @MockBean
    private GoogleUserFinderByEmail finderByEmail;

    @MockBean
    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    private MockMvc mockMvc;

    private static List<GoogleUser> googleUsers;

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    private static String correctUserId;
    private static String wrongUserId;

    private static String correctUserEmail;
    private static String wrongUserEmail;

    @BeforeAll
    public static void setUp() {
        googleUsers = Collections.singletonList(new GoogleUser("123", "Johnson",
                "John", "johnson", "johnJackson@gmail.com", "en"));
        correctUserId = "123";
        wrongUserId = "125";
        correctUserEmail = "johnJackson@gmail.com";
        wrongUserEmail = "wrong@gmail.com";
    }

    @Test
    void getUsers() throws Exception {
        Mockito.when(googleUserService.findAll())
                .thenReturn(googleUsers);
        MvcResult result =
                mockMvc.perform(get("/googleUsers")
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
        assertEquals(jsonMapper.writeValueAsString(googleUsers),
                result.getResponse().getContentAsString());
        Mockito.verify(googleUserService, Mockito.times(1)).findAll();
    }

    @Test
    void getUserById() throws Exception {
        Mockito.when(finderById.findUser(correctUserId))
                .thenReturn(googleUsers.get(0));
        MvcResult result =
                mockMvc.perform(get("/googleUsers/" + correctUserId)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
        assertEquals(jsonMapper.writeValueAsString(googleUsers.get(0)),
                result.getResponse().getContentAsString());
        Mockito.verify(finderById, Mockito.times(1))
                .findUser(correctUserId);

        Mockito.when(finderById.findUser(wrongUserId)).thenReturn(null);
        mockMvc.perform(get("/googleUsers/" + wrongUserId))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getUserByEmail() throws Exception {
        Mockito.when(finderByEmail.findUser(correctUserEmail))
                .thenReturn(googleUsers.get(0));
        MvcResult result =
                mockMvc.perform(get("/googleUsers/email/" + correctUserEmail)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
        assertEquals(jsonMapper.writeValueAsString(googleUsers.get(0)),
                result.getResponse().getContentAsString());
        Mockito.verify(finderByEmail, Mockito.times(1))
                .findUser(correctUserEmail);

        Mockito.when(finderByEmail.findUser(wrongUserEmail)).thenReturn(null);
        mockMvc.perform(get("/googleUsers/email/" + wrongUserEmail))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateUserById() throws Exception {
        GoogleUser updatedUser = googleUsers.get(0);
        updatedUser.setFirstName("NewName");
        updatedUser.setLastName("NewLastName");
        Mockito.when(finderById.findUser(correctUserId))
                .thenReturn(googleUsers.get(0));
        MvcResult mvcResult =
                mockMvc.perform(put("/googleUsers/" + correctUserId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(updatedUser)))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
        Mockito.verify(googleUserService, Mockito.times(1)).update(updatedUser);
        assertEquals(jsonMapper.writeValueAsString(updatedUser), mvcResult.getResponse()
                .getContentAsString());
    }

    @Test
    void updateUserByEmail() {

    }

    @Test
    void delete() {
    }
}