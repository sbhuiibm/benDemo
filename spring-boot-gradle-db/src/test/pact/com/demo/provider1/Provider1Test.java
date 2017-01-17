package com.demo.provider1;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import com.amazonaws.util.IOUtils;

//@RunWith(PactRunner.class) // Say JUnit to run tests with custom Runner
//@Provider("ProductProvider") // Set up name of tested provider
//@PactBroker(host="ec2-54-255-167-96.ap-southeast-1.compute.amazonaws.com", port = "8009") 
public class Provider1Test {
   	

    //@TestTarget // Annotation denotes Target that will be used for tests
    //public final Target target = new HttpTarget("ec2-54-169-248-149.ap-southeast-1.compute.amazonaws.com", 8001);


}
