package io.recruitment.assessment.api.configuration;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Faker getFakeBean(){
        return new Faker();
    }
}
