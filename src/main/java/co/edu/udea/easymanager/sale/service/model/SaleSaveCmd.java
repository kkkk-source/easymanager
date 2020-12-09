package co.edu.udea.easymanager.sale.service.model;

import co.edu.udea.easymanager.sale.model.Sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleSaveCmd {

    @NotNull
    @NotBlank
    private Long saleId;

    @NotNull
    @NotBlank
    private Long product;

    @NotNull
    @NotBlank
    private Integer amount;

    public static Sale toModel(@NotNull SaleSaveCmd saleToCreateCmd) {
        return Sale.builder().saleId(saleToCreateCmd.getSaleId()).product(saleToCreateCmd.getProduct())
                .amount(saleToCreateCmd.getAmount()).build();
    }
}
