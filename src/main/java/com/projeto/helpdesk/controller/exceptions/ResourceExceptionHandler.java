package com.projeto.helpdesk.controller.exceptions;

import com.projeto.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.projeto.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice //usado para tratamento global de erros no aplicativo Spring MVC. Ele também tem controle
// total sobre o corpo da resposta e o código de status.


public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class) //funciona no Controller level e é ativo somente para esse Controller
    // específico não globalmente para o aplicativo inteiro.
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Object not found",
                ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Violação de dados",
                ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
