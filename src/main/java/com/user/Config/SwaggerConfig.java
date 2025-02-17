package com.user.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI customeApi() {
		return new OpenAPI().info(new Info().title("User Management System")
				.description("using Crud use,And Thymelef added").version("0.1"));

	}
}
