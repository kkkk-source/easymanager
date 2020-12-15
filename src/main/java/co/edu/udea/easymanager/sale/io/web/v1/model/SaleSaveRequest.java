package co.edu.udea.easymanager.sale.io.web.v1.model;

import co.edu.udea.easymanager.sale.service.model.SaleSaveCmd;
import co.edu.udea.easymanager.sale.model.SaleProduct;

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
public class SaleSaveRequest {

    @NotNull
    @NotBlank
    private BigDecimal price;

    private List<SaleProduct> products;

    public static SaleSaveCmd toModel(SaleSaveRequest saleToCreate) {
        return SaleSaveCmd.builder() .price(saleToCreate.getPrice())
                .products(saleToCreate.getProducts())
                .build();
    }
}
