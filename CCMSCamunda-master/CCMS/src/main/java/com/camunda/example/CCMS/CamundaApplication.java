package com.camunda.example.CCMS;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication("CCMS")
public class CamundaApplication {

  public static void main(String... args) {
    SpringApplication.run(CamundaApplication.class, args);
  }

}
