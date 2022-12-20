package com.bootcamp.java.parameter.web.mapper;

import com.bootcamp.java.parameter.domain.ProductParameter;
import com.bootcamp.java.parameter.web.model.ProductParameterModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductParameterMapper {
    ProductParameter modelToEntity(ProductParameterModel model);

    ProductParameterModel entityToModel(ProductParameter event);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget ProductParameter entity, ProductParameter updateEntity);
}
