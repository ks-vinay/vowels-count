package com.luxsoft.assignment.vowelscount.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.luxsoft.assignment.vowelscount.controller"))
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Luxsoft Assignment API")
                .description("Luxsoft Assignment API reference for developers")
                .contact("ksvinay.12@gmail.com").license("Vinay KS")
                .licenseUrl("ksvinay.12@gmail.com").version("1.0").build();
    }
}
