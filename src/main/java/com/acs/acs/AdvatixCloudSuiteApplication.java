package com.acs.acs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
//@Component
//@OpenAPIDefinition(info = @Info(title = "My API", version = "1.0", description = "API Documentation"))
public class AdvatixCloudSuiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvatixCloudSuiteApplication.class, args);
	}

}
