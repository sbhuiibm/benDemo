package com.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.common.UserRequest;
import com.demo.common.UserResponse;
import com.demo.datahandler.UserDataHandler;

@RestController
@RequestMapping("/user")
public class TestRestEndpoint {
	
	@Autowired
	private UserDataHandler userDataHandler;
	
	    public TestRestEndpoint() {
	    }
	    
	    @CrossOrigin(origins = "*")
	    @RequestMapping(value="/getUser", method= RequestMethod.GET)	    
	    @ResponseBody
	    public  UserResponse getUser() {
	    	UserResponse userResponse = new UserResponse();
	    	userResponse=userDataHandler.doGetUser(1);
	    	
	    	return userResponse;
	    }

	    
	    @CrossOrigin(origins = "*")
	    @RequestMapping(value="/postUser", method= RequestMethod.POST)	    
	    @ResponseBody
	    public  UserResponse greetings(@RequestBody UserRequest userRequest) {
	    	UserResponse userResponse = new UserResponse();
	    	userResponse=userDataHandler.doGetUser(userRequest.getUserid());
	    	
	    	return userResponse;
	    }

}
	
