package co.edu.udea.easymanager.sale.service;

import co.edu.udea.easymanager.sale.model.Sale;
import co.edu.udea.easymanager.sale.service.model.SaleSaveCmd;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleGateway saleGateway;

    @Override
    public List<Sale> findAllFetchSales() {
        List<Sale> salesFound = saleGateway.findAll();
        return salesFound;
    }

    @Override
    public Sale findById(@NotNull Long id) {
        Sale saleFound = saleGateway.findBySaleId(id);
        return saleFound;
    }

    @Override
    public Sale create(@NotNull SaleSaveCmd saleToCreateCmd) {
        Sale saleToCreate = SaleSaveCmd.toModel(saleToCreateCmd);
        Sale saleCreated = saleGateway.save(saleToCreate);
        return saleCreated;
    }
}
