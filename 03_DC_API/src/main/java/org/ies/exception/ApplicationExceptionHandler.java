package org.ies.exception;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<AppException> handleAppException(ApplicationException exception){
        AppException appException = new AppException();
        appException.setExceptionCode("EX987");
        appException.setDescription(exception.getMessage());
        return new ResponseEntity<>(appException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
