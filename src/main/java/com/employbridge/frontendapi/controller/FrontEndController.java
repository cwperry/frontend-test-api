package com.employbridge.frontendapi.controller;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.employbridge.frontendapi.domain.User;
import com.employbridge.frontendapi.service.FrontEndService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
        LOG.debug("FrontEndApi/happy-path called");
        return frontEndService.happyPath();
    }

    @GetMapping(value = "/users")
    @ResponseBody
    public List<User> retrieveUsers() {
        LOG.debug("FrontEndApi/users called");
        return frontEndService.retrieveUsers();
    }

    @GetMapping(value = "/users/{id}")
    @ResponseBody
    public User retrieveUser(@PathVariable int id) {
        LOG.debug("FrontEndApi/users/{id} called");
        return frontEndService.retrieveUser(id);
    }
}
