package com.deom.consumer1;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import au.com.dius.pact.provider.junit.loader.PactBroker;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Rule;
import org.junit.Test;

import com.amazonaws.util.IOUtils;

@PactBroker(host="ec2-54-255-167-96.ap-southeast-1.compute.amazonaws.com/", port = "8009")
public class Consumer1Test {

    @Rule
    public PactProviderRule greetingProvider = new PactProviderRule("GreetingProvider", this);
	
    @Pact(provider="GreetingProvider" , consumer = "GreetingConsumer")
    public PactFragment createPactFragment(PactDslWithProvider builder) {

        return builder.given("Get memrber name without member id")
                .uponReceiving("uponReceiving")
                .path("/benTest/greetings")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("Hello World!")
                .toFragment();                
    }
    
    
    @Pact(provider="GreetingProvider" , consumer = "GreetingConsumer")
    public PactFragment createPactFragment2(PactDslWithProvider builder) {

        return builder.given("Get memrber name with member id")
                .uponReceiving("uponReceiving")
                .path("/benTest/greetings2")
                .method("GET")
                .query("userid=1")
                .willRespondWith()
                .status(200)
                .body("Hello Ben!")
                .toFragment();                
    }

    
    @Test
    @PactVerification(value = {"GreetingProvider", "Get memrber name without member id"}, fragment ="createPactFragment")
    public void shouldReturnSuccess() throws IOException {
    	
        Request request = Request.Get(this.greetingProvider.getConfig().url() + "/benTest/greetings");
        HttpResponse httpResponse = request.execute().returnResponse();
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        InputStream content = httpResponse.getEntity().getContent();
        assertEquals("Hello World!", IOUtils.toString(content));
        

    }
    
    
    @Test
    @PactVerification(value = {"GreetingProvider", "Get memrber name with member id"}, fragment ="createPactFragment2")
    public void shouldReturnMemberSuccess() throws IOException {
    	
        Request request = Request.Get(this.greetingProvider.getConfig().url() + "/benTest/greetings2?userid=1");
        HttpResponse httpResponse = request.execute().returnResponse();
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        InputStream content = httpResponse.getEntity().getContent();
        assertEquals("Hello Ben!", IOUtils.toString(content));
        

    }

}
