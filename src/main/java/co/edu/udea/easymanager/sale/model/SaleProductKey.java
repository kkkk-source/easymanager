package co.edu.udea.easymanager.sale.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SaleProductKey implements Serializable {

    @Column(name = "sale_id")
    private Long saleId;

    @Column(name = "product_id")
    private Long productId;

}
