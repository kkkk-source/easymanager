package co.edu.udea.easymanager.product.service.model;

import co.edu.udea.easymanager.product.model.Product;

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
public class ProductSaveCmd {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private BigDecimal price;

    @NotBlank
    @Size(min = 0, max = 280)
    private String description;

    public static Product toModel(@NotNull ProductSaveCmd productToCreateCmd) {
        return Product.builder().name(productToCreateCmd.getName()).price(productToCreateCmd.getPrice())
                .description(productToCreateCmd.getDescription()).build();
    }
}
