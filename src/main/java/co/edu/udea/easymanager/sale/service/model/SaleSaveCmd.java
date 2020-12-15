package co.edu.udea.easymanager.sale.service.model;

import co.edu.udea.easymanager.sale.model.Sale;
import co.edu.udea.easymanager.sale.model.SaleProduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleSaveCmd {

    @NotNull
    @NotBlank
    private BigDecimal price;

    private List<SaleProduct> products;

    public static Sale toModel(@NotNull SaleSaveCmd saleToCreateCmd) {
        return Sale.builder().price(saleToCreateCmd.getPrice())
                .products(saleToCreateCmd.getProducts())
                .build();
    }
}
