package com.fillow.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        String jwtSchemeName = "JWT TOKEN"; //인증이름

        SecurityRequirement securityRequirement =
                new SecurityRequirement().addList(jwtSchemeName); //보안에 jwt 토큰 요구

        SecurityScheme securityScheme = new SecurityScheme()
                .name(jwtSchemeName)
                .type(SecurityScheme.Type.HTTP) //jwt는 http 헤더 기반 인증
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(
                        new io.swagger.v3.oas.models.Components()
                                .addSecuritySchemes(jwtSchemeName, securityScheme)
                ) //스웨거에 jwt 토큰 등록
                .info(new Info()
                        .title("Fillow API")
                        .version("1.0")
                        .description("Fillow API Docs"));
    }
}