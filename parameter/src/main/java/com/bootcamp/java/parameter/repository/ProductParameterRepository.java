package com.bootcamp.java.parameter.repository;

import com.bootcamp.java.parameter.domain.ProductParameter;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductParameterRepository extends ReactiveMongoRepository<ProductParameter, String> {
    Mono<ProductParameter> findTopByProductCodeAndClientTypeAndClientProfileAndActive(String productCode, String clientType, String clientProfile, Boolean active);
}
