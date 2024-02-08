package org.ies.exception;

import org.springframework.stereotype.Component;

@Component
public class ApplicationException extends RuntimeException {

    public ApplicationException(){
        super();
    }
    public ApplicationException(String msg){
        super(msg);
    }
}
