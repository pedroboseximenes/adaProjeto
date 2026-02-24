package com.ada.companhia_aerea.core.integrations;

import com.ada.companhia_aerea.domain.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class UserRest {
    @Value("${user.service.base-url}")
    private String userServiceBaseUrl;


    private final RestTemplate restTemplate;

    public UserRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDTO getUserById(Long id, String tokenJwt) {
        String url = userServiceBaseUrl + "/auth/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", tokenJwt); 


        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<UserDTO> response = restTemplate.exchange(
                url,                    
                HttpMethod.GET,         
                requestEntity,        
                UserDTO.class,          
                id                    
        );
        return response.getBody();
    }
}
