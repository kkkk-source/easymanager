package co.edu.udea.easymanager.sale.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SaleId implements Serializable {

    private Long id;

    private Long product;
}
