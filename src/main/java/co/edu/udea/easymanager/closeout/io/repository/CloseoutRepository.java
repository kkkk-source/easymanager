package co.edu.udea.easymanager.closeout.io.repository;

import co.edu.udea.easymanager.closeout.model.Closeout;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface CloseoutRepository extends PagingAndSortingRepository<Closeout, Long>, JpaSpecificationExecutor<Closeout> {
}
