package co.edu.udea.easymanager.sale.service;

import co.edu.udea.easymanager.sale.model.Sale;
import co.edu.udea.easymanager.sale.model.SaleId;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

import java.util.List;

public interface SaleGateway {

    Sale save(@NotNull Sale saleToCreate);

    List<Sale> findBySaleId(@NotNull Long id);
}
