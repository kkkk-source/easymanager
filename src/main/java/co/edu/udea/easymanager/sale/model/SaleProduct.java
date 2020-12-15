package co.edu.udea.easymanager.sale.model;

import co.edu.udea.easymanager.product.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sale_product")
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@IdClass(SaleProductKey.class)
public class SaleProduct implements Serializable {

    @Id
    @JsonIgnore
    private Long saleId;

    @Id
    @JsonIgnore
    private Long productId;

    @Id
    @ManyToOne
    @JsonIgnore
    @MapsId("saleId")
    private Sale sale;

    @Id
    @ManyToOne
    @MapsId("productId")
    private Product product;

    @NotNull
    @NotBlank
    private Integer amount;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
