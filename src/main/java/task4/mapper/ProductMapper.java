package task4.mapper;

import org.mapstruct.Mapper;
import task4.dto.ProductDto;
import task4.model.Product;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);

    List<ProductDto> toProductDtoList(List<Product> products);
}
