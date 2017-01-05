package com.demo.datahandler;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.common.UserResponse;
import com.demo.model.User;
import com.demo.model.UserRepository;

@Component
public class UserDataHandlerImpl implements UserDataHandler {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserResponse doGetUser(long id) {
		UserResponse userResponse = new UserResponse();
		User aUser=new User();
		aUser = userRepository.findOne(id);
		userResponse.setUserid(aUser.getUserid());
		userResponse.setUsername(aUser.getUsername());
		return userResponse;
	}
}
