package ru.redcollar.autoservice.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    private String appName;
    private String authServer;
    private String realm;

    @Autowired
    public OpenApiConfig(
            @Value("${spring.application.name}") String appName,
            @Value("${keycloak.auth-server-url}") String authServer,
            @Value("${keycloak.realm}") String realm) {
        this.appName = appName;
        this.authServer = authServer;
        this.realm = realm;
    }

    @Bean
    OpenAPI springOpenApi() {
        return new OpenAPI()
                .info(apiInfo())
                .components(components())
                .addSecurityItem(securityItem());
    }

    private Info apiInfo() {
        return new Info().title(appName)
                .description(appName + " documentation");
    }

    private Components components() {
        String authUrl =  authServer + "/" + "realms/" + realm + "/protocol/openid-connect/auth";
        return new Components().addSecuritySchemes(
                "keycloak", new SecurityScheme()
                        .type(SecurityScheme.Type.OAUTH2)
                        .description("Oauth2 flow")
                        .flows(new OAuthFlows()
                                .implicit(new OAuthFlow()
                                        .authorizationUrl(authUrl)
                                        .scopes(new Scopes())
                                )
                        )
        );
    }

    private SecurityRequirement securityItem() {
        return new SecurityRequirement()
                .addList("keycloak",
                        Arrays.asList("read", "write"));
    }
}
