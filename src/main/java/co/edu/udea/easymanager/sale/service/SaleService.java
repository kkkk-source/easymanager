package co.edu.udea.easymanager.sale.service;

import co.edu.udea.easymanager.sale.model.Sale;
import co.edu.udea.easymanager.sale.service.model.SaleSaveCmd;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

import java.util.List;

public interface SaleService {

    Sale create(@NotNull SaleSaveCmd saleToCreateCmd);

    List<Sale> createAll(@NotNull List<SaleSaveCmd> salesToCreateCmd);

    List<Sale> findBySaleId(@NotNull Long id);

}
