package com.individual.project.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@RestController
public class APIExceptionHandler {

    @ExceptionHandler(value = APIRequestException.class)
    public ResponseEntity<Object> handleApiException(APIRequestException exception) {
        APIException apiException = new APIException(BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(apiException, BAD_REQUEST);
    }
}
