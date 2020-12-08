package co.edu.udea.easymanager.product.service;

import co.edu.udea.easymanager.product.model.Product;
import co.edu.udea.easymanager.product.service.model.ProductSaveCmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ProductGateway productGateway;

    public ProductServiceImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public Product create(@NotNull ProductSaveCmd productToCreateCmd) {
        logger.debug("Begin create productToCreateCmd = {}", productToCreateCmd);
        Product productToCreate = ProductSaveCmd.toModel(productToCreateCmd);
        Product productCreated  = productGateway.save(productToCreate);
        logger.debug("End create productCreated = {}", productCreated);
        return productCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(@NotNull Long id) {
        logger.debug("Begin findById id = {}", id);
        Product productFound = productGateway.findById(id);
        logger.debug("End findById userFound = {}", productFound);
        return productFound;
    }

    @Override
    public void deleteById(@NotNull Long id) {
        logger.debug("Begin deleteById id = {}", id);
        productGateway.deleteById(id);
        logger.debug("End deleteById");
    }

    @Override
    public Product update(@NotNull Long id, @NotNull ProductSaveCmd productToUpdateCmd) {
        logger.debug("Begin update id = {}, productToUpdateCmd = {}", id, productToUpdateCmd);
        Product productInDataBase = findById(id);
        Product productToUpdate = productInDataBase.toBuilder()
                .name(productToUpdateCmd.getName()).price(productToUpdateCmd.getPrice())
                .description(productToUpdateCmd.getDescription())
                .build();
        Product productUpdated = productGateway.update(productToUpdate);
        logger.debug("End update productUpdated = {}", productUpdated);
        return productUpdated;
    }
}
