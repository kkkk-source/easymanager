package co.edu.udea.easymanager.closeout.io.web.v1.model;

import co.edu.udea.easymanager.closeout.model.Closeout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CloseoutListResponse {

    private Long id;

    private BigDecimal price;

    private LocalDateTime updatedDate;

    public static CloseoutListResponse fromModel(Closeout closeout) {
        return CloseoutListResponse.builder().id(closeout.getId()).price(closeout.getPrice())
                .updatedDate(closeout.getUpdatedDate()).build();
    }
}
