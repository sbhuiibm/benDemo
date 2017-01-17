package com.demo.rest;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.common.UserRequest;
import com.demo.common.UserResponse;
import com.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRestEndpointTest {
	
	//@Autowired
    private TestRestTemplate restTemplate;

    //@Before
    public void setUp() throws Exception {

    }

   // @After
    public void tearDown() throws Exception {

    }

   // @Test
    public void shouldReplyUser() throws Exception {
    	UserRequest userRequest = new UserRequest();
    	userRequest.setUserid(1);
	
    	HttpEntity<UserRequest> request = new HttpEntity<>(userRequest);
    	UserResponse response = restTemplate.postForObject("/user/postUser", request,UserResponse.class);
 
        assertThat(response.getUsername()).isEqualTo("ben");

    }
    



}
