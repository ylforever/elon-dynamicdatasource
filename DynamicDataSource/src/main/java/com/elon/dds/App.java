package com.elon.dds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    private static Logger log = LogManager.getLogger(App.class);
    
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
        log.info("Start dynamic data source success.");
    }
}
