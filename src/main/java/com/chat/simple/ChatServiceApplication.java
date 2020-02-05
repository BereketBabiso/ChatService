package com.chat.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.databind.ObjectMapper;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan(basePackages = "com.chat.simple")
@EnableSwagger2
public class ChatServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ChatServiceApplication.class, args);
  }

  @Bean
  public Docket productApi() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.chat.simple")).build();
  }
  
  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

}
