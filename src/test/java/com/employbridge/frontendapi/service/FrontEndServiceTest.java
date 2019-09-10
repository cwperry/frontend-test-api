package com.employbridge.frontendapi.service;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import java.net.URI;

import static com.employbridge.frontendapi.service.FrontEndService.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class FrontEndServiceTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private RestOperations restOperations;

    private FrontEndService service;

    @Before
    public void beforeTests() {
        service = new FrontEndService(restOperations);
    }

    @Test
    public void happy_path() {
        URI uri = new DefaultUriBuilderFactory(URL).builder().build();
        when(restOperations.getForObject(uri, String.class)).thenReturn("bar");

        String value = service.happyPath();

        assertThat(value, is("bar"));
    }

}