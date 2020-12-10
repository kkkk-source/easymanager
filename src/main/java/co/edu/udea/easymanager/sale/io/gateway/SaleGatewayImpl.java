package co.edu.udea.easymanager.sale.io.gateway;

import co.edu.udea.easymanager.shared.web.exception.ResourceNotFoundException;
import co.edu.udea.easymanager.sale.io.repository.SaleRepository;
import co.edu.udea.easymanager.sale.model.Sale;
import co.edu.udea.easymanager.sale.model.SaleId;
import co.edu.udea.easymanager.sale.service.SaleGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Repository
class SaleGatewayImpl implements SaleGateway {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String RESOURCE_NOT_FOUND = "Sale not found";
    private SaleRepository saleRepository;

    public SaleGatewayImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public Sale save(@NotNull Sale saleToCreate) {
        logger.debug("Begin save saleToCreate = {}", saleToCreate);
        final Sale saleToBeCreated = saleToCreate.toBuilder()
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        final Sale saleCreated = saleRepository.save(saleToBeCreated);
        logger.debug("End save saleCreated = {}", saleCreated);
        return saleCreated;
    }

    @Override
    public Sale findById(@NotNull Long id) {
        logger.debug("Begin findById id = {}", id);
        Sale saleFound = saleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        logger.debug("End findById saleFound = {}", saleFound);
        return saleFound;
    }

    @Override
    public List<Sale> findBySaleId(@NotNull Long id) {
        logger.debug("Begin findById id = {}", id);
        List<Sale> salesFound = saleRepository.findBySaleId(id);
        logger.debug("End findById salesFound = {}", salesFound);
        return salesFound;
    }

    @Override
    public Sale update(@NotNull Sale saleToUpdate) {
        logger.debug("Begin update saleToUpdate = {}", saleToUpdate);
        final Sale saleToBeUpdated = saleToUpdate.toBuilder()
            .updatedDate(LocalDateTime.now())
            .build();
        final Sale saleUpdated = saleRepository.save(saleToBeUpdated);
        logger.debug("End update saleUpdated = {}", saleUpdated);
        return saleUpdated;
    }
}
