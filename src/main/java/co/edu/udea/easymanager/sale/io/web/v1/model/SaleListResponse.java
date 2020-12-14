package co.edu.udea.easymanager.sale.io.web.v1.model;

import co.edu.udea.easymanager.sale.model.Sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.math.BigDecimal;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleListResponse {

    private Long id;
    
    private BigDecimal price;

    public static SaleListResponse fromModel(Sale sale) {
        return SaleListResponse.builder().id(sale.getId())
                .price(sale.getPrice())
                .build();
    }
}
