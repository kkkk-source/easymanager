package co.edu.udea.easymanager.sale.io.web.v1.model;

import co.edu.udea.easymanager.sale.model.Sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleSaveResponse {

    private Long id;

    private Long saleId;

    private Long product;

    private Integer amount;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    public static SaleSaveResponse fromModel(Sale sale) {
        return SaleSaveResponse.builder().id(sale.getId()).saleId(sale.getSaleId())
                .product(sale.getProduct()).amount(sale.getAmount())
                .createdDate(sale.getCreatedDate())
                .updatedDate(sale.getUpdatedDate())
                .build();
    }
}
