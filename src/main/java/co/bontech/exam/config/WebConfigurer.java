package co.bontech.exam.config;

import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
public class WebConfigurer implements ServletContextInitializer {

    private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    private final Environment env;

    public WebConfigurer(Environment env) {
        this.env = env;
    }

    @Override
    public void onStartup(ServletContext servletContext) {
        if (env.getActiveProfiles().length != 0) {
            log.info("Web application configuration, using profiles: {}", (Object[]) env.getActiveProfiles());
        }

        log.info("Web application fully configured");
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        Map<String, CorsConfiguration> nm = new HashMap<>();
        CorsConfiguration v=new CorsConfiguration();
        v.setAllowedOrigins(List.of("*"));
        v.setAllowedMethods(List.of("GET","POST","OPTIONS","PUT","DELETE"));
        v.setAllowedHeaders(List.of("*"));

        v.setAllowCredentials(false);
        nm.put("/**",v);
        source.setCorsConfigurations(nm);
        return new CorsFilter(source);
    }
}
