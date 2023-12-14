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
        source.registerCorsConfiguration("localhost:3000", config);
        source.registerCorsConfiguration("localhost:8080", config);
        source.registerCorsConfiguration("api.chasango.com", config);
        source.registerCorsConfiguration("web.chasango.com", config);
        source.registerCorsConfiguration("chasango.com:3000", config);
        source.registerCorsConfiguration("chasango.com:8080", config);
        return new CorsFilter(source);
    }
}
