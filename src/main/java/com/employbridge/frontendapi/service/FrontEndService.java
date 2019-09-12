package com.employbridge.frontendapi.service;

import com.employbridge.frontendapi.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.util.List;

@Service
public class FrontEndService {
    private static final Logger LOG = LoggerFactory.getLogger(FrontEndService.class);

    // TODO: 9/9/19 Make @VisibleForTesting annotation
    static final String BASE_URL = "http://middle-tier:8080";

    private final RestOperations restOperations;

    @Autowired
    public FrontEndService(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    public String happyPath() {
        LOG.debug("FrontEndService.happyPath() called");
        URI uri = new DefaultUriBuilderFactory(BASE_URL + "/happy-path").builder().build();
        return restOperations.getForObject(uri, String.class);
    }

    public List<User> retrieveUsers() {
        LOG.debug("FrontEndService.retrieveUsers() called");
        return restOperations.exchange(
                BASE_URL + "/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {}).getBody();
    }

    public User retrieveUser(int id) {
        LOG.debug("FrontEndService.retrieveUser() called");
        if (id == 87) {
            LOG.error("User Id is 87. Bad things happen.");
            throw new UnsupportedOperationException("User Id is 87. Bad things happen.");
        }
        return restOperations.getForObject(String.format("%s/users/%d", BASE_URL, id), User.class);
    }
}
