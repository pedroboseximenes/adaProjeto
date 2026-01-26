package com.ada.companhia_aerea.core.integrations;

import com.ada.companhia_aerea.domain.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class UserRest {
    @Value("${user.service.base-url:http://localhost:8081}")
    private String userServiceBaseUrl;


    private final RestTemplate restTemplate;

    public UserRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDTO getUserById(Long id) {
        String url = userServiceBaseUrl + "/auth/{id}";
        return restTemplate.getForObject(url, UserDTO.class, id);
    }
}
