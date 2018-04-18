package com.dhenton9000.birt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BirtApplication {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {

            ErrorPage custom404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
            container.addErrorPages(custom404Page);

        });
    }
    
    /*
    
    use to support CORS
    https://spring.io/guides/gs/rest-service-cors/
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //  registry.addMapping("/birt/**");
                /*
                CorsRegistration t = registry.addMapping("/**");
                // t = t.allowedMethods("GET","HEAD","POST","PUT","DELETE");
                 t = t.allowedOrigins("*");
      
                t.allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS");
                // .allowedOrigins("*");
                //.allowMethods("GET","HEAD","POST","PUT","DELETE");
                */
                
                 CorsRegistration t = registry.addMapping("/**")
                         .allowedOrigins("*")
                         .allowedMethods("GET","HEAD","POST",
                                 "PUT","DELETE","OPTIONS") ;
            }
        };
    }
 
    
    
   
    @Bean
    public Docket birtApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("birt")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/birt/.*"))
                .build();
    }

    @Bean
    public Docket securityApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("sec")
                .apiInfo(secInfo())
                .select()
                .paths(regex("/sec/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact c = new Contact("Don Henton", "http://donhenton.com", null);
        return new ApiInfoBuilder()
                .title("BIRT Database")
                .description("BIRT Database access")
                .contact(c)
                .license("Apache License Version 2.0")
                .version("1.0")
                .build();
    }

    private ApiInfo secInfo() {
        Contact c = new Contact("Don Henton", "http://donhenton.com", null);
        return new ApiInfoBuilder()
                .title("Security Sytem")
                .description("Security System")
                .contact(c)
                .license("Apache License Version 2.0")
                .version("1.0")
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(BirtApplication.class, args);
    }
}
