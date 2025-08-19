package ru.dse.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dse.payment.dto.PaymentRequest;
import ru.dse.payment.dto.PaymentResponce;
import ru.dse.payment.dto.ProductDto;
import ru.dse.payment.service.PaymentService;

import java.util.List;

import static ru.dse.payment.util.Constance.API_PAYMENT;

@RestController
@RequestMapping(API_PAYMENT)
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{userId}")
    public List<ProductDto> getProductsByUserId(@PathVariable Long userId) {
        return paymentService.getProductsByUserId(userId);
    }

    @PostMapping
    public PaymentResponce createPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.makePayment(paymentRequest);
    }
}