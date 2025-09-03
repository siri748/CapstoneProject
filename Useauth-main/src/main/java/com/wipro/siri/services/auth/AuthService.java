package com.wipro.siri.services.auth;

import com.wipro.siri.dto.SignupRequest;
import com.wipro.siri.dto.UserDto;

public interface AuthService 
{

	UserDto signupUser(SignupRequest signupRequest);
	boolean hasUserWithEmail(String email);
}
