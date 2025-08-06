package task4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import task4.dto.ProductDto;
import task4.service.ProductService;

import java.util.List;

import static task4.util.Constance.API_PRODUCTS;

@RestController
@RequestMapping(API_PRODUCTS)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public void save (@RequestBody ProductDto dto) {
        productService.create(dto);
    }

    @GetMapping("/user/{userId}")
    public List<ProductDto> getByUserId(@PathVariable Long userId) {
        return productService.getProductsByUserId(userId);
    }

    @GetMapping("/{productId}")
    public ProductDto getById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }
}