package com.deom.consumer1;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactBroker;

import com.amazonaws.util.IOUtils;
import com.demo.common.GreetingsResponse;
import com.demo.common.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class userProviderTest {

	@Rule
    public PactProviderRule productProvider = new PactProviderRule("ProductProvider",this);
    
    @Pact(consumer = "GreetingProvider")
    public PactFragment createPactFragment2(PactDslWithProvider builder) {
        PactDslJsonBody expectedBody = new PactDslJsonBody()
                .integerType("userid")
                .stringType("username");
    	
        return builder
                .given("Member Hello")
                .uponReceiving("uponReceiving")
                .path("/user/postUser")
                .method("POST")
                .matchHeader("Content-Type", "application/json; charset=UTF-8")
                .body("{\"userid\": 1}")
                .willRespondWith()
                .status(HttpStatus.SC_OK)
                .body(expectedBody)              
                .toFragment();                
    }
    
    @Test
    @PactVerification(value ={"ProductProvider", "Member Hello"}, fragment ="createPactFragment2")
    public void testProvider() throws IOException {

    	Request request = Request.Post(this.productProvider.getConfig().url() + "/user/postUser")
    			.bodyString("{\"userid\": 1}",ContentType.APPLICATION_JSON);
    	HttpResponse httpResponse = request.execute().returnResponse();
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        InputStream content = httpResponse.getEntity().getContent();
        ObjectMapper mapper = new ObjectMapper();
        UserResponse userDetail = mapper.readValue(IOUtils.toString(content), UserResponse.class);

        assertNotNull(userDetail.getUsername());
        
    } 
    


    

}
