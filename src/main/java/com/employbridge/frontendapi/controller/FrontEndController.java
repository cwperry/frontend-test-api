package com.employbridge.frontendapi.controller;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.employbridge.frontendapi.service.FrontEndService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FrontEndController {

    private static final Logger LOG = LoggerFactory.getLogger(FrontEndController.class);

    private final FrontEndService frontEndService;

    public FrontEndController(FrontEndService frontEndService) {
        this.frontEndService = frontEndService;
    }

    @GetMapping(value = "/about")
    @ResponseBody
    public String about() {
        LOG.debug("FrontEndApi/about called");
        return "FrontEndApi";
    }

    @GetMapping(value = "/happy-path")
    @ResponseBody
    public String happyPath() {
        return frontEndService.happyPath();
    }
}
