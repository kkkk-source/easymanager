package co.edu.udea.easymanager.sale.service;

import co.edu.udea.easymanager.sale.model.Sale;
import co.edu.udea.easymanager.sale.service.model.SaleSaveCmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
class SaleServiceImpl implements SaleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private SaleGateway saleGateway;

    public SaleServiceImpl(SaleGateway saleGateway) {
        this.saleGateway = saleGateway;
    }

    @Override
    public List<Sale> create(@NotNull List<SaleSaveCmd> salesToCreateCmd) {
        logger.debug("Begin create salesToCreateCmd = {}", salesToCreateCmd);
        List<Sale> salesCreated = new ArrayList<Sale>();
        salesToCreateCmd.stream().forEach((saleToCreateCmd) -> {
            Sale saleToCreate = SaleSaveCmd.toModel(saleToCreateCmd);
            Sale saleCreated  = saleGateway.save(saleToCreate);
            salesCreated.add(saleCreated);
        });
        logger.debug("End create salesCreated = {}", salesCreated);
        return salesCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sale> findBySaleId(@NotNull Long id) {
        logger.debug("Begin findById id = {}", id);
        List<Sale> salesFound = saleGateway.findBySaleId(id);
        logger.debug("End findById salesFound = {}", salesFound);
        return salesFound;
    }
}
