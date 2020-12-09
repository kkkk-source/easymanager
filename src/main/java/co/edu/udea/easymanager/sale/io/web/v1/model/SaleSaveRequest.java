package co.edu.udea.easymanager.sale.io.web.v1.model;

import co.edu.udea.easymanager.sale.service.model.SaleSaveCmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleSaveRequest {

    @NotNull
    @NotBlank
    private Long product;

    @NotNull
    @NotBlank
    private Integer amount;

    public static SaleSaveCmd toModel(SaleSaveRequest saleToCreate) {
        return SaleSaveCmd.builder().product(saleToCreate.getProduct())
                .amount(saleToCreate.getAmount())
                .build();
    }
}
