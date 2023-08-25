package med.voll.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info().title("Vollmed API")
                        .description("API Rest da aplicação voll.med," +
                                " contendo as funcionalidades de CRUD de médicos e de pacientes," +
                                " além do agendamento e calcelamento de consultas  ")
                        .contact(new Contact().name("equipe backend").email("backend@email.com"))
                        .license(new License().name("Apache 2.0").url("http://voll.med/api.licenca")));

    }
}
