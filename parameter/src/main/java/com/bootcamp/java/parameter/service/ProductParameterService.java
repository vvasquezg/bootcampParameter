package com.bootcamp.java.parameter.service;

import com.bootcamp.java.parameter.domain.ProductParameter;
import com.bootcamp.java.parameter.repository.ProductParameterRepository;
import com.bootcamp.java.parameter.web.mapper.ProductParameterMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProductParameterService {
    @Autowired
    private ProductParameterRepository productParameterRepository;

    @Autowired
    private ProductParameterMapper productParameterMapper;

    public Flux<ProductParameter> findAll(){
        log.debug("findAll executed");
        return productParameterRepository.findAll();
    }

    public Mono<ProductParameter> findById(String id){
        log.debug("findById executed {}", id);
        return productParameterRepository.findById(id);
    }

    public Mono<ProductParameter> findByProductCodeAndClientTypeAndClientProfile(String productCode, String clientType, String clientProfile){
        log.debug("findByCode executed {} - {} - {}", productCode, clientType, clientProfile);
        return productParameterRepository.findTopByProductCodeAndClientTypeAndClientProfileAndActive(productCode, clientType, clientProfile,true);
    }

    public Mono<ProductParameter> create(ProductParameter productParameter){
        log.debug("create executed {}", productParameter);
        return productParameterRepository.save(productParameter);
    }

    public Mono<ProductParameter> update(String id, ProductParameter productParameter) {
        log.debug("update executed {}:{}", id, productParameter);
        return productParameterRepository.findById(id)
                .flatMap(dbProductParameter -> {
                    productParameterMapper.update(dbProductParameter, productParameter);
                    return productParameterRepository.save(dbProductParameter);
                });
    }

    public Mono<ProductParameter> delete(String id){
        log.debug("delete executed {}", id);
        return productParameterRepository.findById(id)
                .flatMap(existingProductParameter -> productParameterRepository.delete(existingProductParameter)
                        .then(Mono.just(existingProductParameter)));
    }
}
