package hongik.ce.LostAndFound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class LostAndFoundApplication {

	public static void main(String[] args) {
		SpringApplication.run(LostAndFoundApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			private static final long MAX_AGE_SECS = 3600L;
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000", "http://localhost:8080")
						.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") // METHOD 추가
						.allowedHeaders("*") // 모든 헤더 추가
						.maxAge(MAX_AGE_SECS);
			}
		};
	}
}
