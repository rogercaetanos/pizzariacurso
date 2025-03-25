package com.itb.inf3an25.pizzariacurso.exceptions;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// @ControllerAdvice : Toda exceção será capturada pela classe que contém a referida anotação

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
    String [] arrayMessage;

    // Método responsável em tratar erros relacionados ao SERVIDOR Código: 500
    
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object>globalException(Exception ex) {
       LocalDateTime localDateTime = LocalDateTime.now(zoneId); 
       String errorMessageDescription = ex.getLocalizedMessage();
       System.out.println(errorMessageDescription);
       errorMessageDescription = "Ocorreu um erro interno no servidor:";
       arrayMessage = errorMessageDescription.split(":");
       ErrorMessage errorMessage = new ErrorMessage(localDateTime, arrayMessage, HttpStatus.INTERNAL_SERVER_ERROR);
       return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
   // Método responsável em tratar erros relacionados ao CLIENTE Código: 404 (Recurso não encontrado)
   @ExceptionHandler(value = {NotFound.class})
   public ResponseEntity<Object>notFoundException(NotFound ex) {
      LocalDateTime localDateTime = LocalDateTime.now(zoneId); 
      String errorMessageDescription = ex.getLocalizedMessage();
      System.out.println(errorMessageDescription);
      if(errorMessageDescription == null) errorMessageDescription = ex.toString();
       arrayMessage = errorMessageDescription.split(":");
      ErrorMessage errorMessage = new ErrorMessage(localDateTime, arrayMessage, HttpStatus.NOT_FOUND);
      return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
   }

   // Método responsável em tratar erros relacionados ao CLIENTE Código: 400 (Url´s mal formadas, falta de validação etc...)

   @ExceptionHandler(value = {BadRequest.class})
   public ResponseEntity<Object>badRequestException(BadRequest ex) {
      LocalDateTime localDateTime = LocalDateTime.now(zoneId); 
      String errorMessageDescription = ex.getLocalizedMessage();
      System.out.println(errorMessageDescription);
      if(errorMessageDescription == null) errorMessageDescription = ex.toString();
       arrayMessage = errorMessageDescription.split(":");
      ErrorMessage errorMessage = new ErrorMessage(localDateTime, arrayMessage, HttpStatus.BAD_REQUEST);
      return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
   }

}
