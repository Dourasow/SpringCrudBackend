package com.spring_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SourceNotFoundException extends RuntimeException{

  //  public static final long serialVerisonUID = 1L;

   public SourceNotFoundException (String message){
       super(message);
   }
}
