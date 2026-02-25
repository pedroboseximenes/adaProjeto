package com.ada.companhia_aerea.config;


import com.ada.companhia_aerea.adapter.passagem.PassagemJpaAdapter;
import com.ada.companhia_aerea.adapter.passagem.PassagemRepository;
import com.ada.companhia_aerea.core.integrations.UserRest;
import com.ada.companhia_aerea.core.integrations.VooRest;
import com.ada.companhia_aerea.core.passagem.GetPassagemByNameEmailUseCase;
import com.ada.companhia_aerea.core.passagem.PassagemPort;
import com.ada.companhia_aerea.core.passagem.ProcessPassagemUseCase;
import com.ada.companhia_aerea.producers.PassagemProducer;
import com.ada.companhia_aerea.validation.user.ValidatorUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class PassagemBeanConfiguration {
    private final PassagemRepository passagemRepository;

    public PassagemBeanConfiguration(PassagemRepository passagemRepository) {
        this.passagemRepository = passagemRepository;
    }

    @Bean
    PassagemPort passagemRepositoryPort(PassagemRepository jpa) {
        return new PassagemJpaAdapter(jpa);
    }
    @Bean
    GetPassagemByNameEmailUseCase getGetPassagemByNameEmailUseCase(PassagemPort repo, List<ValidatorUser> validatorUserList)   {
        return new GetPassagemByNameEmailUseCase(repo, validatorUserList);
    }

    @Bean
    ProcessPassagemUseCase getProcessPassagemUseCase(PassagemPort repo , 
                                                    UserRest userRest,
                                                    VooRest vooRest,
                                                     PassagemProducer passagemProducer,
                                                     List<ValidatorUser> validatorUserList)   {
        return new ProcessPassagemUseCase(repo, userRest, vooRest , passagemProducer, validatorUserList);
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)  {
        return config.getAuthenticationManager();
    }
    @Bean
    UserRest userRest(RestTemplate restTemplate )   {
        return new UserRest(restTemplate);
    }
    @Bean
    VooRest vooRest(RestTemplate restTemplate )   {
        return new VooRest(restTemplate);
    }
    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        return new RestTemplate(factory);
    }

}