package com.employbridge.frontendapi.controller;

import com.employbridge.frontendapi.service.LoadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LoadController.class)
public class LoadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    LoadService service;

    @Test
    public void load_endpoint() throws Exception {
        mockMvc.perform(
                get("/load-test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Load Test Started"));

        verify(service).testLoad();
    }

    @Test
    public void stop_load_endpoint() throws Exception {
        mockMvc.perform(
                get("/load-test-stop"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Load Test Stopped"));

        verify(service).stopTest();
    }

}