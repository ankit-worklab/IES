package org.ies.Exception;

import org.springframework.stereotype.Component;

@Component
public class AppRegistrationException extends RuntimeException{

    public AppRegistrationException(){
        super();
    }
    public AppRegistrationException(String msg){
        super(msg);
    }
    public AppRegistrationException(String msg,Throwable t){
        super(msg,t);
    }
}
