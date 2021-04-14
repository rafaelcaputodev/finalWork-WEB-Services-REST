package com.caputo.finalWork.Controller.exceptions;

import com.caputo.finalWork.Services.Exceptions.DatabaseException;
import com.caputo.finalWork.Services.Exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(NotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("ERROR:");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(value = DatabaseException.class)
    public ResponseEntity<StandardError> entityDatabase(DatabaseException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Database exception:");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
