package com.wire.qa.migration.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wire.qa.migration.io.ErrorResponse;

@ControllerAdvice
public class AppExceptionMapper {

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> mapException(final IllegalArgumentException exception) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(exception.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

}
