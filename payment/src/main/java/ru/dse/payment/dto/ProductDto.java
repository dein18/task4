package ru.dse.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dse.payment.enums.ProductType;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto implements Serializable {

    private Long id;

    private Long accountNumber;

    private Long balance;

    private ProductType productType;

    private Long userId;
}
