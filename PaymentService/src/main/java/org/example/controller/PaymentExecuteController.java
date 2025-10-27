package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.commonError.CommonErrorResponseDto;
import org.example.dto.paymentExecute.PaymentExecuteRequestDto;
import org.example.dto.paymentExecute.PaymentExecuteResponseDto;
import org.example.service.PaymentExecuteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/payment")
public class PaymentExecuteController {

    private final PaymentExecuteService paymentExecuteService;
    private final RestTemplate restTemplate;

    @PostMapping(value = "/execute/")
    PaymentExecuteResponseDto paymentExecute(@RequestBody PaymentExecuteRequestDto paymentExecuteRequestDto) {
        return paymentExecuteService.paymentExecute(paymentExecuteRequestDto);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    CommonErrorResponseDto handleServerError(Exception exception) {
        return new CommonErrorResponseDto(exception.getClass().getSimpleName(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    CommonErrorResponseDto handleRequestError(Exception exception) {
        return new CommonErrorResponseDto(exception.getClass().getSimpleName(), exception.getMessage());
    }
}
