package com.example.voo.config;


import com.example.voo.adapter.voo.VooJpaAdapter;
import com.example.voo.adapter.voo.VooRepository;
import com.example.voo.core.voo.GetVooByIdUseCase;
import com.example.voo.core.voo.GetVooUseCase;
import com.example.voo.core.voo.UpdateVooUseCase;
import com.example.voo.core.voo.VooPort;
import com.example.voo.validation.voo.ValidatorVoo;
import org.hibernate.sql.Update;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class VooBeanConfiguration {
    private final VooRepository vooRepository;

    public VooBeanConfiguration(VooRepository vooRepository) {
        this.vooRepository = vooRepository;
    }
    @Bean
    VooPort vooRepositoryPort(VooRepository jpa) {
        return new VooJpaAdapter(jpa);
    }
    @Bean
    GetVooUseCase getGetVooUseCase(VooPort repo, List<ValidatorVoo> validatorVooList)   {
        return new GetVooUseCase(repo, validatorVooList);
    }
    @Bean
    GetVooByIdUseCase getGetVooByIdUseCase(VooPort repo)   {
        return new GetVooByIdUseCase(repo);
    }

    @Bean
    UpdateVooUseCase getUpdateVooUseCase(VooPort repo)   {
        return new UpdateVooUseCase(repo);
    }
}