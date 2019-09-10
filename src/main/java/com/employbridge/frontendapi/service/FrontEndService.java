package com.employbridge.frontendapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;

@Service
public class FrontEndService {

    // TODO: 9/9/19 Make @VisibleForTesting annotation
    static final String URL = "http://middle-tier:8080/happy-path";

    private final RestOperations restOperations;

    @Autowired
    public FrontEndService(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    public String happyPath() {
        URI uri = new DefaultUriBuilderFactory(URL).builder().build();
        return restOperations.getForObject(uri, String.class);
    }
}
