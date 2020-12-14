package co.edu.udea.easymanager.sale.io.web.v1.model;

import co.edu.udea.easymanager.sale.model.Sale;
import co.edu.udea.easymanager.product.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleSaveResponse {

    private Long id;

    private BigDecimal price;

    private List<Product> products;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    public static SaleSaveResponse fromModel(Sale sale) {
        return SaleSaveResponse.builder().id(sale.getId()).price(sale.getPrice())
                .products(sale.getProducts()).createdDate(sale.getCreatedDate())
                .updatedDate(sale.getUpdatedDate())
                .build();
    }
}
