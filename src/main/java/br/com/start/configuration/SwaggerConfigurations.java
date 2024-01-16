package br.com.start.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfigurations {
	
	@Bean
	public Docket portalApi() {
		return new Docket(DocumentationType.SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors.basePackage("br.com.start.controller"))
		.paths(PathSelectors.ant("/**"))
		.build()
		.apiInfo(apiInfo());
		
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API Clientes")
				.description("CRUD de clientes")
				.version("1.0")
				.license("Apache license Version 2.0")
				.licenseUrl("http://www.apache.org/licences/LICENSE-2.0")
				.contact(new Contact("", null, ""))
				.build();
	}
}
