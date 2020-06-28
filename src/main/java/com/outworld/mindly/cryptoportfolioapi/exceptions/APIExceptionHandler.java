package com.outworld.mindly.cryptoportfolioapi.exceptions;

import com.outworld.mindly.cryptoportfolioapi.dto.ResponseDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String FAILURE_MSG = "Failure!";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        ResponseDTO<Object> responseDTO = ResponseDTO.builder().message(FAILURE_MSG)
                .body(errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toArray()).build();

        return ResponseEntity.badRequest().body(responseDTO);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {

        ResponseDTO<Object> responseDTO = ResponseDTO.builder().message(FAILURE_MSG)
                .body(ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toArray()).build();

        return ResponseEntity.badRequest().body(responseDTO);
    }
}
