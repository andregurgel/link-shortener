package br.dev.andregurgel.linkshortener.api.config.properties;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class GlobalProperties {
    private final SystemRoutes systemRoutes = new SystemRoutes();

    @Data
    public static class SystemRoutes {
        private String url = "http://localhost:8080";
    }
}
