package org.arun.springoauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

/**
 * Spring boot application start up class. You may run this class to start the application.
 * <p>
 * We are excluding {@link SecurityAutoConfiguration} and {@link UserDetailsServiceAutoConfiguration}
 * to disable spring default basic authentication.
 * </p>
 * Note: If you are using Spring 1.x you may exclude only {@link SecurityAutoConfiguration}.
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,
                                  UserDetailsServiceAutoConfiguration.class})
public class Startup {

  public static void main(String[] args) {
    SpringApplication.run(Startup.class, args);
  }
}
