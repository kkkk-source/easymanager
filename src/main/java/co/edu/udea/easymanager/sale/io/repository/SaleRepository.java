package co.edu.udea.easymanager.sale.io.repository;

import co.edu.udea.easymanager.sale.model.Sale;
import co.edu.udea.easymanager.sale.model.SaleId;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

import java.util.List;

@Repository
public interface SaleRepository extends PagingAndSortingRepository<Sale, SaleId>, JpaSpecificationExecutor<Sale> {
    List<Sale> findById(@NotNull Long id);
}
