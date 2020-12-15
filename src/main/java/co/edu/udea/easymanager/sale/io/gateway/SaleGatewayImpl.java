package co.edu.udea.easymanager.sale.io.gateway;

import co.edu.udea.easymanager.shared.web.exception.ResourceNotFoundException;
import co.edu.udea.easymanager.sale.io.repository.SaleRepository;
import co.edu.udea.easymanager.sale.model.Sale;
import co.edu.udea.easymanager.sale.service.SaleGateway;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;
import static java.util.Objects.nonNull;

@Repository
class SaleGatewayImpl implements SaleGateway {

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public List<Sale> findAll() {
        List<Sale> salesFound = 
            StreamSupport.stream(saleRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
        return salesFound;
    }

    @Override
    public Sale findBySaleId(@NotNull Long id) {
        Optional<Sale> saleFound = saleRepository.findById(id);
        return saleFound.get();
    }

    @Override
    public Sale save(@NotNull Sale saleToCreate) {
        final Sale saleToBeCreated = saleToCreate.toBuilder()
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        final Sale saleCreated = saleRepository.save(saleToBeCreated);
        return saleCreated;
    }

}
