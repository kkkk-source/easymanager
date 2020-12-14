package co.edu.udea.easymanager.sale.io.repository;

import co.edu.udea.easymanager.sale.model.Sale;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface SaleRepository extends PagingAndSortingRepository<Sale, Long>, JpaSpecificationExecutor<Sale> {

}
