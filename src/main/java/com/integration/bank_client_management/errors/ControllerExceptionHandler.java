package com.integration.bank_client_management.errors;

import org.joda.time.DateTime;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDetail> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ExceptionDetail message = new ExceptionDetail(HttpStatus.NOT_FOUND.value(), new DateTime().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionDetail> alreadyExistsException(AlreadyExistsException ex, WebRequest request) {
        ExceptionDetail message = new ExceptionDetail(HttpStatus.CONFLICT.value(), new DateTime().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException .class)
    public ResponseEntity<ExceptionDetail> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ExceptionDetail message = new ExceptionDetail(HttpStatus.BAD_REQUEST.value(), new DateTime().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDetail> illegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ExceptionDetail message = new ExceptionDetail(HttpStatus.NOT_ACCEPTABLE.value(), new DateTime().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetail> internalServerException(InternalServerException ex, WebRequest request) {
        ExceptionDetail message = new ExceptionDetail(HttpStatus.INTERNAL_SERVER_ERROR.value(), new DateTime().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ExceptionDetail>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionDetail message = new ExceptionDetail(status.value(), new DateTime().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionDetail message = new ExceptionDetail(status.value(), new DateTime().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ExceptionDetail message = new ExceptionDetail(status.value(), new DateTime().toString(), String.join(", ", errorMessages), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
    }
}