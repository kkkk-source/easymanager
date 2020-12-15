package co.edu.udea.easymanager.sale.io.repository;

import co.edu.udea.easymanager.sale.model.SaleProduct;
import co.edu.udea.easymanager.sale.model.SaleProductKey;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface SaleProductRepository extends PagingAndSortingRepository<SaleProduct, SaleProductKey>, JpaSpecificationExecutor<SaleProduct> {
}
