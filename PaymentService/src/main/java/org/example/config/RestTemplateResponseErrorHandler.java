package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.commonError.CommonErrorResponseDto;
import org.example.exception.PaymentExecuteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatusCode statusCode = response.getStatusCode();
        return statusCode.is4xxClientError() || statusCode.is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if  (response.getStatusCode().is5xxServerError()) {
            CommonErrorResponseDto commonErrorResponseDto = new ObjectMapper().readValue(
                    response.getBody(),
                    CommonErrorResponseDto.class
            );
            String errorDesc = "Ошибка при вызове внешнего сервиса\n" + commonErrorResponseDto.message();
            throw new PaymentExecuteException(errorDesc);
        }
    }

}
