package co.edu.udea.easymanager.closeout.service.model;

import co.edu.udea.easymanager.closeout.model.Closeout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CloseoutSaveCmd {

    @NotNull
    @NotBlank
    private BigDecimal price;

    public static Closeout toModel(@NotNull CloseoutSaveCmd closeoutToCreateCmd) {
        return Closeout.builder().price(closeoutToCreateCmd.getPrice()).build();
    }
}
