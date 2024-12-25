package com.example.matchmaking.exception;

import com.example.matchmaking.enums.Status;
import com.example.matchmaking.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> response = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            response.put(fieldName, message);
        });
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleBookNotFoundEx(ResourceNotFoundException ex) {
        UserExceptionHandler handler = new UserExceptionHandler();
        handler.setStatus(Status.FAILURE);
        handler.setHttpStatus(HttpStatus.NOT_FOUND);
        handler.setErrorMsg("Id doesn't present" + ex.getMessage());
        log.error("Status code : Exception : [{}] " + HttpStatus.NOT_FOUND, handler.getErrorMsg());
        return new ResponseEntity<>(handler, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<BaseResponse<Object, String>> handleUserNotFoundException(UserNotFoundException ex) {
        BaseResponse<Object, String> response = new BaseResponse<>();
        response.setData(null);
        response.setStatus(Status.FAILURE);
        response.setDetail(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object, String>> handleGenericException(Exception ex) {
        BaseResponse<Object, String> response = new BaseResponse<>();
        response.setData(null);
        response.setStatus(Status.FAILURE);
        response.setDetail("An error occurred : " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
