package co.edu.udea.easymanager.product.io.web.v1.model;

import co.edu.udea.easymanager.product.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSaveResponse {

    private Long id;

    private String name;

    private BigDecimal price;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    public static ProductSaveResponse fromModel(Product product) {
        return ProductSaveResponse.builder().id(product.getId())
                .name(product.getName()).price(product.getPrice())
                .createdDate(product.getCreatedDate())
                .updatedDate(product.getUpdatedDate())
                .build();
    }
}
