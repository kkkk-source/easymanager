package co.edu.udea.easymanager.product.io.web.v1.model;

import co.edu.udea.easymanager.product.service.model.ProductSaveCmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSaveRequest {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private BigDecimal price;

    @NotBlank
    @Size(min = 0, max = 280)
    private String description;

    public static ProductSaveCmd toModel(ProductSaveRequest productToCreate) {
        return ProductSaveCmd.builder().name(productToCreate.getName()).price(productToCreate.getPrice())
                .description(productToCreate.getDescription()).build();
    }
}
