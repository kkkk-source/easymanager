package co.edu.udea.easymanager.sale.service;

import co.edu.udea.easymanager.sale.model.Sale;
import co.edu.udea.easymanager.sale.service.model.SaleSaveCmd;

import co.edu.udea.easymanager.product.model.Product;
import co.edu.udea.easymanager.product.service.model.ProductSaveCmd;
import co.edu.udea.easymanager.product.service.ProductService;
import co.edu.udea.easymanager.product.service.ProductServiceImpl;

import co.edu.udea.easymanager.closeout.model.Closeout;
import co.edu.udea.easymanager.closeout.service.model.CloseoutSaveCmd;
import co.edu.udea.easymanager.closeout.service.CloseoutService;
import co.edu.udea.easymanager.closeout.service.CloseoutServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private SaleGateway saleGateway;
    private ProductService productService;
    private CloseoutService closeoutService;

    public SaleServiceImpl(SaleGateway saleGateway, ProductService productService,
            CloseoutService closeoutService) {
        this.saleGateway = saleGateway;
        this.closeoutService = closeoutService;
        this.productService = productService;
    }

    @Override
    public List<Sale> create(@NotNull List<SaleSaveCmd> salesToCreateCmd) {
        logger.debug("Begin create salesToCreateCmd = {}", salesToCreateCmd);
        List<Sale> salesCreated = new ArrayList<Sale>();
        CloseoutSaveCmd closeoutToCreateCmd = CloseoutSaveCmd.builder()
                .price(new BigDecimal(0.0))
                .build();
        Closeout closeoutCreated = closeoutService.create(closeoutToCreateCmd);
        Long saleId = closeoutCreated.getId();
        BigDecimal total = closeoutCreated.getPrice();
        for (SaleSaveCmd saleToCreateCmd : salesToCreateCmd) {
            saleToCreateCmd.setSaleId(saleId);
            Sale saleToCreate = SaleSaveCmd.toModel(saleToCreateCmd);
            Sale saleCreated  = saleGateway.save(saleToCreate);
            salesCreated.add(saleCreated);
            Product productRelated = productService.findById(
                    saleCreated.getProduct());
            BigDecimal price = productRelated.getPrice();
            price = price.multiply(new BigDecimal(saleCreated.getAmount()));
            total = total.add(price);
        }
        CloseoutSaveCmd closeoutToUpdateCmd = closeoutToCreateCmd;
        closeoutToUpdateCmd.setPrice(total);
        closeoutService.update(closeoutCreated.getId(), closeoutToUpdateCmd);
        logger.debug("End create salesCreated = {}", salesCreated);
        return salesCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public Sale findById(@NotNull Long id) {
        logger.debug("Begin findById id = {}", id);
        Sale saleFound = saleGateway.findById(id);
        logger.debug("End findById userFound = {}", saleFound);
        return saleFound;
    }

    @Override
    public Sale update(@NotNull Long id, @NotNull SaleSaveCmd saleToUpdateCmd) {
        logger.debug("Begin update id = {}, saleToUpdateCmd = {}", id, saleToUpdateCmd);
        Sale saleInDataBase = findById(id);
        Sale saleToUpdate = saleInDataBase.toBuilder()
            .saleId(saleToUpdateCmd.getSaleId()).product(saleToUpdateCmd.getProduct())
            .amount(saleToUpdateCmd.getAmount())
            .build();
        Sale saleUpdated = saleGateway.update(saleToUpdate);
        logger.debug("End update saleUpdated = {}", saleUpdated);
        return saleUpdated;
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
