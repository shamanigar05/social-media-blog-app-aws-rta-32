package com.shama.leanring.blog.socialmediablogapp.socialmediablogapp;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Social Media Blog Application",
				description = "Spring Boot Social Media Blog App REST APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Shama Nigar",
						email = "shamanigar05@gmail.com",
						url ="https://github.com/shamanigar05/social-media-blog-app-aws-rta-32"
				),
				license = @License(
						name = "Apache 1.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Social Media Blog App Documentation",
				url = "http://localhost:9090/swagger-ui/index.html#/"
		)
)
public class SocialMediaBlogAppApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaBlogAppApplication.class, args);
	}

}
