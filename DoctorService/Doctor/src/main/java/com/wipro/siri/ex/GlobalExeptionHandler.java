package com.wipro.siri.ex;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExeptionHandler {

	
	@ExceptionHandler(USER_NOT_FOUND_EXCEPTON.class)
	public ResponseEntity<ErrrorMessage> handleUserNotFoundException(USER_NOT_FOUND_EXCEPTON ex)
	{
		ErrrorMessage errrorMessage=new ErrrorMessage(ex.getMessage(),"USER_NOT_FOUND_EXCEPTON");
		return new ResponseEntity<>(errrorMessage,HttpStatus.NOT_FOUND);
	}
	
	//fdsfhgadfgv
	///ef;badgdgoldfg
	
}
