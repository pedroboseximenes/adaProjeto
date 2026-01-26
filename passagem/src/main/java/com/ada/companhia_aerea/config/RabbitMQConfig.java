package com.ada.companhia_aerea.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;


@Configuration
public class RabbitMQConfig {
    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        JsonMapper objectMapper = new JsonMapper();
        return new JacksonJsonMessageConverter(objectMapper, "*");
    }
}
