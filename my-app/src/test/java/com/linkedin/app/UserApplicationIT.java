package com.linkedin.app;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import javax.naming.Name;

import static org.junit.jupiter.api.Assertions.*;

// loads the entire spring application context, including all the beans and configurations, it can even customise what configuration classes to load and what type of environment to make
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApplicationIT {

    //restTemplate can be used to interact with the application just like a real client would
    @Autowired
    private TestRestTemplate restTemplate;

    private static final String NAME = "Jane Doe";
    private static final String EMAIL = "jane.doe@gmail.com";

    // first: send our request to the running service
    @Test
    public void createUserTest(){
        // Add application Json header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // request HTTP eneity is a json object
        HttpEntity<String> request = new HttpEntity<>("{\"name\": \"" + NAME +"\", \"email\":\"" + EMAIL + "\"}", headers);


        ResponseEntity<User> response = restTemplate.postForEntity("/users", request, User.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertNotNull(response.getBody().getId());
    }

    @Test
    public void createUserAndRetrive(){
        // create a user
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =  new HttpEntity<>("{\"name\": \"" + NAME +"\", \"email\":\"" + EMAIL + "\"}", headers);

        ResponseEntity<User> response = restTemplate.postForEntity("/users", request, User.class);

        assertNotNull(response.getBody());

        // get a user by id
        ResponseEntity<User> fetchedUserResponse = restTemplate.getForEntity("/users/" + response.getBody().getId(), User.class);
        assertEquals(HttpStatus.OK, fetchedUserResponse.getStatusCode());
        assertNotNull(fetchedUserResponse.getBody());
        assertEquals(NAME, fetchedUserResponse.getBody().getName());
        assertEquals(EMAIL, fetchedUserResponse.getBody().getEmail());
        assertEquals(response.getBody().getId(), fetchedUserResponse.getBody().getId());
    }

    @Test
    public void getUserByIdNotFound() {
        // Attempt to retrieve a user that does not exist
        ResponseEntity<User> response = restTemplate.getForEntity("/users/9999", User.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
