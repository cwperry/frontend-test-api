package com.employbridge.frontendapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FrontEndController {

    @GetMapping(value = "/about")
    @ResponseBody
    public String about() {
        return "FrontEndApi";
    }
}
