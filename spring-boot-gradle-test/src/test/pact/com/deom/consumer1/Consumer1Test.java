package com.deom.consumer1;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import com.amazonaws.util.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Mike Chung
 */
public class Consumer1Test {


    @Rule
    public PactProviderRule provider1 = new PactProviderRule("Provider1", this);

    @Pact(provider="Provider1", consumer = "Consumer1")
    public PactFragment createPactFragment(PactDslWithProvider builder) {

        return builder.given("Given a state")
                .uponReceiving("uponReceiving")
                .path("/benTest/greetings2")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("Hello Ben!")
                .toFragment();
    }

    @Test
    @PactVerification({"Provider1", "Given a state"})
    public void shouldReturnSuccess() throws IOException {
        Request request = Request.Get(this.provider1.getConfig().url() + "/benTest/greetings2");
        HttpResponse httpResponse = request.execute().returnResponse();
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        InputStream content = httpResponse.getEntity().getContent();
        assertEquals("Hello Ben!", IOUtils.toString(content));
    }

}
