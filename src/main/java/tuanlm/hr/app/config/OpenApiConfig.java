package tuanlm.hr.app.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

/**
 * The Class OpenApiConfig.
 * Config Swagger-OpenApi for document
 */
@Configuration
@SecurityScheme(
	name = "bearerAuth",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	scheme = "Bearer"
)
public class OpenApiConfig {
	
	/**
	 * Config open api.
	 *
	 * @return the open API
	 */
	@Bean
	public OpenAPI configOpenApi() {
		return new OpenAPI()
				.servers(Arrays.asList(
						new Server().url("http://localhost:8084").description("Local Server"), 
						new Server().url("http://34.230.45.99:8084").description("EC2 Server")
				))
				.info(new Info()
						.title("HR-App API")
						.description("Api Document V1")
						.contact(new Contact()
								.email("tuanvipandpro@gmail.com")
								.name("TuanLM")
								.url("https://www.facebook.com/tuanlm110/"))
						.license(new License()
								.name("Apache 2.0")
								.url("http://www.apache.org/licenses/LICENSE-2.0.html"))
						.version("1.0"));
	}
}
