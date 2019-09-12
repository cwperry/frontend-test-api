package com.employbridge.frontendapi.controller;

import com.employbridge.frontendapi.domain.ImmutableUser;
import com.employbridge.frontendapi.domain.User;
import com.employbridge.frontendapi.service.FrontEndService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FrontEndController.class)
public class FrontEndControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FrontEndService service;

    @Test
    public void about_end_point() throws Exception {
        mockMvc.perform(
                get("/about"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("FrontEndApi"));
    }

    @Test
    public void happy_path_endpoint() throws Exception {
        when(service.happyPath()).thenReturn("foo");

        mockMvc.perform(
                get("/happy-path"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("foo"));
    }

    @Test
    public void retrieve_users_endpoint() throws Exception {
        User first = ImmutableUser.builder()
                .withId(1)
                .withFirstName("Chester")
                .withLastName("Nimitz")
                .withEmail("cincpac@navy.mil")
                .withGender("Male")
                .withCompanyName("Pacific Fleet")
                .build();

        User second = ImmutableUser.builder()
                .withId(2)
                .withFirstName("Raymond")
                .withLastName("Spruance")
                .withEmail("fifth_fleet@navy.mil")
                .withGender("Male")
                .withCompanyName("Pacific Fleet")
                .build();

        List<User> users = new ArrayList<>();
        users.add(first);
        users.add(second);

        when(service.retrieveUsers()).thenReturn(users);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(
                get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(users)));
    }

    @Test
    public void retrieve_user_endpoint() throws Exception {
        User user = ImmutableUser.builder()
                .withId(1)
                .withFirstName("Chester")
                .withLastName("Nimitz")
                .withEmail("cincpac@navy.mil")
                .withGender("Male")
                .withCompanyName("Pacific Fleet")
                .build();

        when(service.retrieveUser(1)).thenReturn(user);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(
                get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(user)));
    }


}