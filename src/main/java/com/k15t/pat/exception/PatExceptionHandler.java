package com.k15t.pat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PatExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({PatUserException.class})
  public ResponseEntity<String> handlePatUserExistsException(PatUserException exception) {
    this.logger.error(exception.getMessage(), exception);
    System.out.println(exception.getMessage() + " :: " + exception.getEmail());
    return new ResponseEntity<>(String.format("%s :: %s", exception.getMessage(), exception.getEmail()), HttpStatus.BAD_REQUEST);
  }


}
