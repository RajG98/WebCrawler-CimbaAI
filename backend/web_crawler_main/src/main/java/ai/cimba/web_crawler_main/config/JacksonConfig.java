package ai.cimba.web_crawler_main.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.scala.DefaultScalaModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        // Create the default ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Register the Scala module to handle case classes
        objectMapper.registerModule(new DefaultScalaModule());

        return objectMapper;
    }
}
