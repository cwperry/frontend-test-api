package com.employbridge.frontendapi.service;

import com.employbridge.frontendapi.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class LoadService {

    private static final Logger LOG = LoggerFactory.getLogger(LoadService.class);

    private static final String BASE_URL = "http://frontend:8080";
    private static final int THREAD_SIZE = 3;

    private final RestOperations rest;
    private final Random random;
    private ScheduledExecutorService service;

    @Autowired
    public LoadService(RestOperations rest) {
        this.rest = rest;
        random = new Random();
        service = Executors.newScheduledThreadPool(THREAD_SIZE);
    }

    public void testLoad() {
        if (service.isShutdown()) {
            service = Executors.newScheduledThreadPool(THREAD_SIZE);
        }

        for (int i = 0; i < THREAD_SIZE; i++) {
            Runnable runnable = () -> {
                try {
                    rest.getForObject(String.format("%s/users/%d", BASE_URL, randomUserId()), User.class);
                } catch (RestClientException e) {
                    LOG.error("Catching the error", e);
                }
            };

            service.scheduleAtFixedRate(runnable, 100, 950, TimeUnit.MILLISECONDS);
        }
    }

    public void stopTest() {
        service.shutdown();
    }

    private int randomUserId() {
        return random.nextInt(105);
    }
}
