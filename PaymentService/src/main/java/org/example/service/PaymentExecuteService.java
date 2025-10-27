package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.clientProduct.request.ClientProductRequestDto;
import org.example.dto.clientProduct.response.ClientProductResponseDto;
import org.example.dto.clientProductUpdate.ClientProductUpdateRequestDto;
import org.example.dto.clientProductUpdate.ClientProductUpdateResponseDto;
import org.example.dto.commonError.CommonErrorResponseDto;
import org.example.dto.paymentExecute.PaymentExecuteRequestDto;
import org.example.dto.paymentExecute.PaymentExecuteResponseDto;
import org.example.exception.PaymentExecuteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class PaymentExecuteService {

    private final RestTemplate restTemplateClientProductsClient;

    public PaymentExecuteResponseDto paymentExecute(PaymentExecuteRequestDto paymentExecuteRequestDto) {
        long productId = paymentExecuteRequestDto.productId();
        ClientProductRequestDto clientProductDto = new ClientProductRequestDto(productId);
        String getProductPath = "/products/" + productId;
        ClientProductResponseDto clientProductResponseDto = restTemplateClientProductsClient.
                getForObject(getProductPath, ClientProductResponseDto.class);

        BigDecimal currentProductBalance = clientProductResponseDto.balance();
        BigDecimal writeoffAmount = paymentExecuteRequestDto.writeoffAmount();

        if (writeoffAmount.compareTo(currentProductBalance) > 0) {
            BigDecimal needAmount = writeoffAmount.subtract(currentProductBalance);
            throw new PaymentExecuteException("Недостаточно средств на балансе. Не хватает " + needAmount);
        } else {
            BigDecimal newBalance = currentProductBalance.subtract(writeoffAmount);
            ClientProductUpdateRequestDto updateProductDto = new ClientProductUpdateRequestDto(productId, newBalance);
            String postProductPath = "/products/";
            ClientProductUpdateResponseDto updateProductResponseDto = restTemplateClientProductsClient.
                    patchForObject(postProductPath, updateProductDto, ClientProductUpdateResponseDto.class);
            return new PaymentExecuteResponseDto(updateProductResponseDto.status(), updateProductResponseDto.message());
        }
    }

    @ExceptionHandler(value = PaymentExecuteException.class)
    public PaymentExecuteResponseDto handleException(Exception exception) {
        return new PaymentExecuteResponseDto("ERROR", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    CommonErrorResponseDto handleRequestError(Exception exception) {
        return new CommonErrorResponseDto("ERROR", exception.getMessage());
    }
}
