package ru.marketplace.server.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.marketplace.server.entities.products.Product;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

//    Product toEntity(Product)
}
