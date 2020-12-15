package co.edu.udea.easymanager.sale.io.gateway;

import co.edu.udea.easymanager.shared.web.exception.ResourceNotFoundException;
import co.edu.udea.easymanager.sale.io.repository.SaleProductRepository;
import co.edu.udea.easymanager.sale.model.SaleProduct;
import co.edu.udea.easymanager.sale.service.SaleProductGateway;

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
class SaleProductGatewayImpl implements SaleProductGateway {

    @Autowired
    private SaleProductRepository saleProductRepository;

    @Override
    public SaleProduct save(@NotNull SaleProduct associationToCreate) {
        final SaleProduct associationToBeCreated = associationToCreate.toBuilder()
                .createdDate(LocalDateTime.now()).updatedDate(LocalDateTime.now())
                .build();
        final SaleProduct associationCreated = 
                saleProductRepository.save(associationToBeCreated);
        return associationCreated;
    }
}
