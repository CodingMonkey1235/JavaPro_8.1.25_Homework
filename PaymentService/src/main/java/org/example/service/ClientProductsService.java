package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.clientProducts.request.ClientProductsRequestDto;
import org.example.dto.clientProducts.response.ClientProductsResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class ClientProductsService {

    private final RestTemplate restTemplateClientProductsClient;

    @Transactional
    public ClientProductsResponseDto getClientProducts(ClientProductsRequestDto clientProductsRequestDto) {
        String clientProductPath = "/products/user/" + clientProductsRequestDto.clientId() + "/";
        return restTemplateClientProductsClient
                .getForObject(clientProductPath, ClientProductsResponseDto.class);
    }
}
