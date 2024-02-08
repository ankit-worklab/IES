package org.ies.exception;

import lombok.Data;

@Data
public class AppException{
   String exceptionCode;
   String description;
   Throwable t;
}
