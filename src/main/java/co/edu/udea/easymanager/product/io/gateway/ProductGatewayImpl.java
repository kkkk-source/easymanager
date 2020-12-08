package co.edu.udea.easymanager.product.io.gateway;

import co.edu.udea.easymanager.shared.web.exception.ResourceNotFoundException;
import co.edu.udea.easymanager.product.io.repository.ProductRepository;
import co.edu.udea.easymanager.product.model.Product;
import co.edu.udea.easymanager.product.service.ProductGateway;

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
class ProductGatewayImpl implements ProductGateway {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String RESOURCE_NOT_FOUND = "Product not found";
    private ProductRepository productRepository;

    public ProductGatewayImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(@NotNull Product productToCreate) {
        logger.debug("Begin save productToCreate = {}", productToCreate);
        final Product productToBeCreated = productToCreate.toBuilder()
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        final Product productCreated = productRepository.save(productToBeCreated);
        logger.debug("End save productCreated = {}", productCreated);
        return productCreated;
    }

    @Override
    public Product findById(@NotNull Long id) {
        logger.debug("Begin findById id = {}", id);
        Product productFound = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        logger.debug("End findById productFound = {}", productFound);
        return productFound;
    }

    @Override
    public void deleteById(@NotNull Long id) {
        logger.debug("Begin deleteById id = {}", id);
        findById(id);
        productRepository.deleteById(id);
        logger.debug("End deleteById");
    }

    @Override
    public Product update(@NotNull Product productToUpdate) {
        logger.debug("Begin update productToUpdate = {}", productToUpdate);
        final Product productToBeUpdated = productToUpdate.toBuilder()
                .updatedDate(LocalDateTime.now())
                .build();
        final Product productUpdated = productRepository.save(productToBeUpdated);
        logger.debug("End update productUpdated = {}", productUpdated);
        return productUpdated;
    }
}
