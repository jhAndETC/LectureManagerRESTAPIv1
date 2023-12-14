package hyundai.cc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class AppConfig {
    @Bean
    public CorsFilter corsFilter() {
        // Configuration for the CORS filter
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // ... configure as needed
        source.registerCorsConfiguration("localhost:8080", config);
        source.registerCorsConfiguration("api.chasango.com", config);
        return new CorsFilter(source);
    }
}
