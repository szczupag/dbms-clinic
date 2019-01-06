package pl.moscicki.clinicbackend.infrastructure;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket SwaggerApi() {
    return new Docket(DocumentationType.SWAGGER_2)
            .groupName("moscicky")
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(getSwaggerPaths())
            .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
            .title("DBMS clinic")
            .description("Api for DbmsClinic")
            .version("1.0.1")
            .build();
  }

  private Predicate<String> getSwaggerPaths() {
    return or(
            regex("/api.*"),
            regex("/test.*"));
  }
}