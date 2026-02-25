package com.ada.companhia_aerea.core.integrations;

import com.ada.companhia_aerea.domain.Voo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

public class VooRest {
    @Value("${voo.service.base-url}")
    private String vooServiceBaseUrl;


    private final RestTemplate restTemplate;

    public VooRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Voo> getUserById(Long id, String tokenJwt) {
        String url = vooServiceBaseUrl + "/voo/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", tokenJwt); 


        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Voo> response = restTemplate.exchange(
                url,                    
                HttpMethod.GET,         
                requestEntity,        
                Voo.class,          
                id                    
        );
        Optional<Voo> vooOptional = Optional.ofNullable(response.getBody());

        return vooOptional;
    }

    public void updateVoo(Long id, String tokenJwt) {
        String url = vooServiceBaseUrl + "/voo/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", tokenJwt); 


        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                url, 
                HttpMethod.POST, 
                requestEntity, 
                Void.class, // Indica que não esperamos um corpo de resposta
                id
        );
}
}
