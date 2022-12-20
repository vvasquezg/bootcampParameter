package com.bootcamp.java.parameter.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductParameterModel {
    @JsonIgnore
    private String id;
    @NotBlank(message = "Product Code cannot be null or empty")
    private String productCode;
    @NotBlank(message = "Client Type cannot be null or empty")
    private String clientType;
    @NotBlank(message = "Client Profile cannot be null or empty")
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
