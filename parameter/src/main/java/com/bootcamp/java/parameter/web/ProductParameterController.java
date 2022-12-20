package com.bootcamp.java.parameter.web;

import com.bootcamp.java.parameter.domain.ProductParameter;
import com.bootcamp.java.parameter.service.ProductParameterService;
import com.bootcamp.java.parameter.web.mapper.ProductParameterMapper;
import com.bootcamp.java.parameter.web.model.ProductParameterModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/productParameter")
public class ProductParameterController {
    @Value("${spring.application.name}")
    String name;

    @Value("${server.port}")
    String port;

    @Autowired
    private ProductParameterService productParameterService;

    @Autowired
    private ProductParameterMapper productParameterMapper;

    @GetMapping
    public Mono<ResponseEntity<Flux<ProductParameterModel>>> getAll(){
        log.info("getAll executed");
        return Mono.just(ResponseEntity.ok()
                .body(productParameterService.findAll()
                        .map(customer -> productParameterMapper.entityToModel(customer))));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductParameterModel>> getById(@PathVariable String id){
        log.info("getById executed {}", id);
        Mono<ProductParameter> response = productParameterService.findById(id);
        return response
                .map(customer -> productParameterMapper.entityToModel(customer))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/getByCodeAndTypeAndProfile/{code}/{type}/{profile}")
    public Mono<ResponseEntity<ProductParameterModel>> getByCodeAndTypeAndProfile(@PathVariable String code, @PathVariable String type, @PathVariable String profile){
        log.info("getByCodeAndTypeAndProfile executed {} - {} - {}", code, type, profile);
        Mono<ProductParameter> response = productParameterService.findByProductCodeAndClientTypeAndClientProfile(code, type, profile);
        return response
                .map(customer -> productParameterMapper.entityToModel(customer))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<ProductParameterModel>> create(@Valid @RequestBody ProductParameterModel request){
        log.info("create executed {}", request);
        return productParameterService.create(productParameterMapper.modelToEntity(request))
                .map(customer -> productParameterMapper.entityToModel(customer))
                .flatMap(c ->
                        Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name,
                                        port, "productParameter", c.getId())))
                                .body(c)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProductParameterModel>> updateById(@PathVariable String id, @Valid @RequestBody ProductParameterModel request){
        log.info("updateById executed {}:{}", id, request);
        return productParameterService.update(id, productParameterMapper.modelToEntity(request))
                .map(customer -> productParameterMapper.entityToModel(customer))
                .flatMap(c ->
                        Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name,
                                        port, "productParameter", c.getId())))
                                .body(c)))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id){
        log.info("deleteById executed {}", id);
        return productParameterService.delete(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
