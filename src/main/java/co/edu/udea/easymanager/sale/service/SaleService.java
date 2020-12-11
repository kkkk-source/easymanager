package co.edu.udea.easymanager.sale.service;

import co.edu.udea.easymanager.sale.model.Sale;
import co.edu.udea.easymanager.sale.service.model.SaleSaveCmd;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

import java.util.List;

public interface SaleService {

    List<Sale> create(@NotNull List<SaleSaveCmd> salesToCreateCmd);

    Sale findById(@NotNull Long id);

    List<Sale> findBySaleId(@NotNull Long id);

    Sale update(@NotNull Long id, @NotNull SaleSaveCmd saleToUpdateCmd);
}
