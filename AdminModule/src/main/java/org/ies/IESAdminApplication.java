package org.ies;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class IESAdminApplication {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(IESAdminApplication.class);
        ApplicationContext context = SpringApplication.run(IESAdminApplication.class,args);
    }
}
