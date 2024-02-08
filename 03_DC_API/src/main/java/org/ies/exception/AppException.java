package org.ies.exception;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AppException {

    private String exceptionCode;
    private String description;
    private LocalDateTime dateAndTime;
}
