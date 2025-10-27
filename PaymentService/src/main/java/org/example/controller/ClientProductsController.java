package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.clientProducts.request.ClientProductsRequestDto;
import org.example.dto.clientProducts.response.ClientProductsResponseDto;
import org.example.service.ClientProductsService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/client")
public class ClientProductsController {

    private final ClientProductsService clientProductsService;

    @GetMapping(value = "/{clientId}/products/")
    ClientProductsResponseDto paymentExecute(@PathVariable("clientId") long clientId) {
        ClientProductsRequestDto clientProductsRequestDto = new ClientProductsRequestDto(clientId);
        return clientProductsService.getClientProducts(clientProductsRequestDto);
    }
}
