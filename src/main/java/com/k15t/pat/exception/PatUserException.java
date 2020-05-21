package com.k15t.pat.exception;

import lombok.Data;

@Data
public class PatUserException extends RuntimeException {

  private String message;
  private String email;

  public PatUserException(String message, String email) {
    super(message);
    this.email = email;
    this.message = message;
  }

  public PatUserException(String message) {
    super(message);
    this.message = message;
  }
}
