package task4.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import task4.dto.ProductDto;
import task4.exeption.EntityNotFoundExeption;
import task4.mapper.ProductMapper;
import task4.model.Product;
import task4.repository.ProductRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public void create(ProductDto dto) {
        Product newProduct = productRepository.save(productMapper.toProduct(dto));
        log.info("Product сохранен: " + newProduct);
    }

    public List<ProductDto> getProductsByUserId(Long userId) {
        return productMapper.toProductDtoList(productRepository.findByUserId(userId));
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundExeption(String.format("Product с id = %s не найден.", id)));
        log.info("Product найден: " + product);
        return productMapper.toProductDto(product);
    }
}
