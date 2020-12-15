package co.edu.udea.easymanager.sale.service;

import co.edu.udea.easymanager.sale.model.Sale;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface SaleGateway {

    List<Sale> findAll();

    Sale findBySaleId(@NotNull Long id);

    Sale save(@NotNull Sale saleToCreate);

}
