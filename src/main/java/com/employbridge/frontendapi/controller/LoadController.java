package com.employbridge.frontendapi.controller;

import com.employbridge.frontendapi.service.LoadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class LoadController {

    private static final Logger LOG = LoggerFactory.getLogger(LoadController.class);

    private final LoadService service;

    @Autowired
    public LoadController(LoadService service) {
        this.service = service;
    }

    @GetMapping(value = "/load-test")
    @ResponseBody
    public String loadTest() {
        LOG.debug("FrontEndApi/load-test called");
        CompletableFuture.runAsync(service::testLoad);
        return "Load Test Started";
    }

    @GetMapping(value = "/load-test-stop")
    @ResponseBody
    public String stopTest() {
        LOG.debug("FrontEndApi/load-test-stop called");
        service.stopTest();
        return "Load Test Stopped";
    }
}
