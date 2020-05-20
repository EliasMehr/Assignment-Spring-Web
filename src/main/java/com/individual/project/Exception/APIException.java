package com.individual.project.Exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class APIException {

    private LocalDateTime timestamp;
    private int status;
    private HttpStatus error;
    private String message;
    private String path;


    public APIException(HttpStatus error, String message) {
        this.error = error;
        this.message = message;
        this.status = this.error.value();
        this.timestamp = LocalDateTime.now();
        this.path = null;

    }
}
