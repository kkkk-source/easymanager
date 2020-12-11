package co.edu.udea.easymanager.sale.service;

import co.edu.udea.easymanager.sale.model.Sale;

import javax.validation.constraints.NotNull;

import java.util.List;

public interface SaleGateway {

    Sale save(@NotNull Sale saleToCreate);

    Sale findById(@NotNull Long id);

    List<Sale> findBySaleId(@NotNull Long id);

    Sale update(@NotNull Sale saleToUpdate);
}
