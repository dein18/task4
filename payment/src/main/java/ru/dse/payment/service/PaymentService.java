package ru.dse.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.dse.payment.exception.PaymentException;
import ru.dse.payment.dto.PaymentRequest;
import ru.dse.payment.dto.PaymentResponce;
import ru.dse.payment.dto.ProductDto;
import ru.dse.payment.exception.ProductNotFoundExeption;

import java.util.List;

import static ru.dse.payment.util.Constance.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final RestClient restClient;

    public List<ProductDto> getProductsByUserId(Long userId) {
        return getProducts(userId);
    }

    public PaymentResponce makePayment(PaymentRequest paymentRequest) {
        List<ProductDto> productDtoList = getProducts(paymentRequest.getUserId());

        ProductDto productDto = productDtoList.stream()
                .filter(dto-> dto.getId().equals(paymentRequest.getProductId()))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundExeption(CODE_404_NOT_FOUND));

        if(productDto.getBalance() < paymentRequest.getAmount()) {
            throw new PaymentException(CODE_409_CONFLICT);
        }
        return new PaymentResponce(PAYMENT_OK, paymentRequest.getAmount(), productDto.getId());
    }

    private List<ProductDto> getProducts(Long userId) {
        var responce = restClient
                .get()
                .uri(URL_PRODUCTS, userId)
                .header("Accept", "application/json")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<ProductDto>>() {
                });
        return responce.getBody();
    }
}
