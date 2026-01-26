package com.ada.companhia_aerea.config;


import com.ada.companhia_aerea.adapter.passagem.PassagemJpaAdapter;
import com.ada.companhia_aerea.adapter.passagem.PassagemRepository;
import com.ada.companhia_aerea.adapter.voo.VooJpaAdapter;
import com.ada.companhia_aerea.adapter.voo.VooRepository;
import com.ada.companhia_aerea.core.passagem.PassagemPort;
import com.ada.companhia_aerea.core.voo.GetVooByIdUseCase;
import com.ada.companhia_aerea.core.voo.GetVooUseCase;
import com.ada.companhia_aerea.core.voo.UpdateVooUseCase;
import com.ada.companhia_aerea.core.voo.VooPort;
import org.hibernate.sql.Update;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.client.RestTemplate;

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
    GetVooUseCase getGetVooUseCase(VooPort repo)   {
        return new GetVooUseCase(repo);
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