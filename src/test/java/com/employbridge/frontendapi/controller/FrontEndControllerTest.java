package com.employbridge.frontendapi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FrontEndController.class)
public class FrontEndControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void about_end_point() throws Exception {
        mockMvc.perform(
                get("/about"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("FrontEndApi"));
    }


}