package com.wipro.siri.dto;



import com.wipro.siri.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {

	private long id;
	private String name;
	private String email;
	private String password;
	private UserRole userRole;


}
