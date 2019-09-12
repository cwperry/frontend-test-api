package com.employbridge.frontendapi.service;

import com.employbridge.frontendapi.domain.ImmutableUser;
import com.employbridge.frontendapi.domain.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.employbridge.frontendapi.service.FrontEndService.BASE_URL;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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
        URI uri = new DefaultUriBuilderFactory(BASE_URL + "/happy-path").builder().build();
        when(restOperations.getForObject(uri, String.class)).thenReturn("bar");

        String value = service.happyPath();

        assertThat(value, is("bar"));
    }

    @Test
    public void users_endpoints_forwards_to_backend_api() {
        User first = ImmutableUser.builder()
                .withId(1)
                .withFirstName("Chester")
                .withLastName("Nimitz")
                .withEmail("cincpac@navy.mil")
                .withGender("Male")
                .withCompanyName("Pacific Fleet")
                .build();

        User second = ImmutableUser.builder()
                .withId(2)
                .withFirstName("Raymond")
                .withLastName("Spruance")
                .withEmail("fifth_fleet@navy.mil")
                .withGender("Male")
                .withCompanyName("Pacific Fleet")
                .build();

        List<User> users = new ArrayList<>();
        users.add(first);
        users.add(second);

        ResponseEntity<List<User>> usersResponse = new ResponseEntity<List<User>>(users, HttpStatus.OK);

        when(restOperations.exchange(
                BASE_URL + "/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {}))
                .thenReturn(usersResponse);

        List<User> retrievedUsers = service.retrieveUsers();
        assertThat(retrievedUsers, is(users));
    }

    @Test
    public void retrieve_user_returns_user() {
        User user = ImmutableUser.builder()
                .withId(1)
                .withFirstName("Chester")
                .withLastName("Nimitz")
                .withEmail("cincpac@navy.mil")
                .withGender("Male")
                .withCompanyName("Pacific Fleet")
                .build();

        when(restOperations.getForObject(BASE_URL + "/users/1", User.class)).thenReturn(user);

        User retrievedUser = service.retrieveUser(1);
        assertThat(retrievedUser, is(user));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void retrieve_user_with_id_of_87_throws_exception() {
        service.retrieveUser(87);
    }

}