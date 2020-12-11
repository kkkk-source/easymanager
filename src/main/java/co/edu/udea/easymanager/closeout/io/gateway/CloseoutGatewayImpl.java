package co.edu.udea.easymanager.closeout.io.gateway;

import co.edu.udea.easymanager.shared.web.exception.ResourceNotFoundException;
import co.edu.udea.easymanager.closeout.io.repository.CloseoutRepository;
import co.edu.udea.easymanager.closeout.model.Closeout;
import co.edu.udea.easymanager.closeout.service.CloseoutGateway;

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
class CloseoutGatewayImpl implements CloseoutGateway {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String RESOURCE_NOT_FOUND = "Closeout not found";
    private CloseoutRepository closeoutRepository;

    public CloseoutGatewayImpl(CloseoutRepository closeoutRepository) {
        this.closeoutRepository = closeoutRepository;
    }

    @Override
    public Closeout save(@NotNull Closeout closeoutToCreate) {
        logger.debug("Begin save closeoutToCreate = {}", closeoutToCreate);
        final Closeout closeoutToBeCreated = closeoutToCreate.toBuilder()
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        final Closeout closeoutCreated = closeoutRepository.save(closeoutToBeCreated);
        logger.debug("End save closeoutCreated = {}", closeoutCreated);
        return closeoutCreated;
    }

    @Override
    public Closeout findById(@NotNull Long id) {
        logger.debug("Begin findById id = {}", id);
        Closeout closeoutFound = closeoutRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        logger.debug("End findById closeoutFound = {}", closeoutFound);
        return closeoutFound;
    }

    @Override
    public Page<Closeout> findAll(@NotNull Pageable pageable) {
        logger.debug("Begin findAll, pageable = {}", pageable);
        Page<Closeout> closeoutsFound = closeoutRepository.findAll(pageable);
        logger.debug("End findAll closeoutFound = {}", closeoutsFound);
        return closeoutsFound;
    }

    @Override
    public Closeout update(@NotNull Closeout closeoutToUpdate) {
        logger.debug("Begin update closeoutToUpdate = {}", closeoutToUpdate);
        final Closeout closeoutToBeUpdated = closeoutToUpdate.toBuilder()
            .updatedDate(LocalDateTime.now())
            .build();
        final Closeout closeoutUpdated = closeoutRepository.save(closeoutToBeUpdated);
        logger.debug("End update saleUpdated = {}", closeoutUpdated);
        return closeoutUpdated;
    }
}
