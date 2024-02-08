package org.ies.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AppRegistrationExHandler {

    @ExceptionHandler(value=AppRegistrationException.class)
    public ResponseEntity<AppException> handleAppRegistrationException(AppRegistrationException exception){
        AppException appException = new AppException();
        appException.setExceptionCode("EX874");
        appException.setDescription(exception.getMessage());
        appException.setExDateTime(LocalDateTime.now());

        return new ResponseEntity<>(appException, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
