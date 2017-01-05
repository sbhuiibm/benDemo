package com.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.context.config.annotation.RefreshScope;


import com.demo.common.GreetingsResponse;
import com.demo.common.UserRequest;
import com.demo.common.UserResponse;
@RefreshScope
@RestController
@RequestMapping("/benTest")
public class TestRestEndpoint {
	
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${privateElb:http://192.168.99.100:8001}")
    private String privateAlb;


	    public TestRestEndpoint() {
	    }
	    
	    @CrossOrigin(origins = "*")
	    @RequestMapping(value="/greetings", method= RequestMethod.GET)	    
	    @ResponseBody
	    public  GreetingsResponse greetings() {
	    	GreetingsResponse greetingsResponse=new GreetingsResponse();
	    	greetingsResponse.setGreetings("Hello, world!");
	        return greetingsResponse;
	    }

	    @CrossOrigin(origins = "*")
	    @RequestMapping(value="/greetings2", method= RequestMethod.GET)
	    @ResponseBody
	    public GreetingsResponse greetings2(@RequestParam(value="userid") long userid) {
	    	GreetingsResponse greetingsResponse=new GreetingsResponse();
	    	
	    	UserRequest userRequest=new UserRequest();
	    	userRequest.setUserid(userid);

	    	HttpEntity<UserRequest> request = new HttpEntity<>(userRequest);
	    	//String privateAlb="http://internal-privateElb-1478920057.ap-southeast-1.elb.amazonaws.com";
	    	//String privateAlb="http://publicalb-2041584895.ap-southeast-1.elb.amazonaws.com";
	    	//String privateAlb="http://192.168.99.100:8001";
	    	String endPoint = privateAlb + "/user/postUser";
	    	System.out.println(endPoint);
	    	UserResponse response = restTemplate.postForObject(endPoint, request,UserResponse.class);
	    	greetingsResponse.setGreetings("Hello, " +response.getUsername() +"!");
	    	
	        return greetingsResponse;
	    }

	    @RequestMapping(value="/greetings3", method= RequestMethod.GET)
	    @ResponseBody
	    public GreetingsResponse greetings3() {
	        
	    	GreetingsResponse greetingsResponse=new GreetingsResponse();
	    	greetingsResponse.setGreetings("test");
	        return greetingsResponse;
	    }

}
	
