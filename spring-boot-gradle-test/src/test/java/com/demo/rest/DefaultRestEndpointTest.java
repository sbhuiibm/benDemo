package com.demo.rest;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.common.GreetingsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DefaultRestEndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldReplyHelloWhenGreetings() throws Exception {

        String result = restTemplate.getForObject("/benTest/greetings", String.class);
        ObjectMapper mapper = new ObjectMapper();
        GreetingsResponse response =mapper.readValue(result, GreetingsResponse.class);
        assertThat(response.getGreetings()).isEqualTo("Hello, world!");

    }

    @Test
    @Ignore
    public void shouldReplyHelloThereWhenGreetings2() throws Exception {

        String result = restTemplate.getForObject("/benTest/greetings2?userid=1", String.class);
        ObjectMapper mapper = new ObjectMapper();
        GreetingsResponse response =mapper.readValue(result, GreetingsResponse.class);
        assertThat(response.getGreetings()).isEqualTo("Hello, ben!");
    }

}