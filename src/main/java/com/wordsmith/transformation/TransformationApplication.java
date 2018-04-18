package com.wordsmith.transformation;

import com.google.common.collect.ImmutableList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@Import(BeanValidatorPluginsConfiguration.class)
public class TransformationApplication {

    @Bean
    public Docket api() {
        final ApiInfo apiInfo = new ApiInfo(
            "Transformation service",
            "Transforms text",
            "0.0.1",
            "http://www.termsofserviceurl.com",
            new Contact("Name", "http://www.url.com", "mail@mail.com"),
            "Some licence",
            "http://www.licenceurl.com",
            ImmutableList.of()
        );

        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .paths(PathSelectors.any())
            .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(TransformationApplication.class, args);
    }

}
