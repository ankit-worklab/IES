package org.ies.Exception;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class AppException {

    String exceptionCode;
    String description;
    LocalDateTime exDateTime;
}
