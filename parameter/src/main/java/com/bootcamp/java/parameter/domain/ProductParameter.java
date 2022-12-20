package com.bootcamp.java.parameter.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "productParameter")
public class ProductParameter {
    @Id
    private String id;
    @NotNull
    private String productCode;
    @NotNull
    private String clientType;
    @NotNull
    private String clientProfile;
    private Long maxProduct;
    private Float commissionAccount;
    private Float commissionTransaction;
    private Long maxTransaction;
    private Integer transactionDay;
    private Integer minimumHolder;
    private Integer minimumSigner;
    private Boolean accountRequired;
    private Boolean cardRequired;
    private Float averageMinimumAmount;
    private Boolean active;
}
