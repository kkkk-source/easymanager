package co.edu.udea.easymanager.sale.service;

import co.edu.udea.easymanager.sale.model.SaleProduct;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface SaleProductGateway {

    SaleProduct save(@NotNull SaleProduct associationToCreate);

}
