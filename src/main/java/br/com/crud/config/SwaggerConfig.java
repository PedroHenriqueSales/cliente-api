package br.com.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiSwaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.crud"))
				.paths(PathSelectors.ant("/api/**"))
				.build()
				.apiInfo(appDetail());
	}

	private ApiInfo appDetail() {

		return new ApiInfo(
				"Cliente API",
				"MVP de cadastro de clientes",
				"0.0.1",
				"Open Source",
				new Contact("Pedro Sales", "", "pedrohsalesf@gmail.com"),
				"",
				"",
				Collections.emptyList()
		);

	}
}
