package com.employbridge.frontendapi.controller;

import com.employbridge.frontendapi.service.FrontEndService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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


}