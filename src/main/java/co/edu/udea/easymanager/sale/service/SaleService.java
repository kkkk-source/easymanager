package co.edu.udea.easymanager.sale.service;

import co.edu.udea.easymanager.sale.model.Sale;
import co.edu.udea.easymanager.sale.service.model.SaleSaveCmd;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface SaleService {

    List<Sale> findAllFetchSales();

    Sale findById(@NotNull Long id);

    Sale create(@NotNull SaleSaveCmd saleToCreateCmd);

}
