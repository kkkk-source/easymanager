package co.edu.udea.easymanager.product.service;

import co.edu.udea.easymanager.product.model.Product;
import co.edu.udea.easymanager.product.service.model.ProductSaveCmd;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface ProductService {

    Product create(@NotNull ProductSaveCmd productToCreateCmd);

    Product findById(@NotNull Long id);

    void deleteById(@NotNull Long id);

    Product update(@NotNull Long id, @NotNull ProductSaveCmd productToUpdateCmd);
}
