package co.edu.udea.easymanager.closeout.service;

import co.edu.udea.easymanager.closeout.model.Closeout;
import co.edu.udea.easymanager.closeout.service.model.CloseoutSaveCmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CloseoutServiceImpl implements CloseoutService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private CloseoutGateway closeoutGateway;

    public CloseoutServiceImpl(CloseoutGateway closeoutGateway) {
        this.closeoutGateway = closeoutGateway;
    }

    @Override
    public Closeout create(@NotNull CloseoutSaveCmd closeoutToCreateCmd) {
        logger.debug("Begin create closeoutToCreateCmd = {}", closeoutToCreateCmd);
        Closeout closeoutToCreate = CloseoutSaveCmd.toModel(closeoutToCreateCmd);
        Closeout closeoutCreated  = closeoutGateway.save(closeoutToCreate);
        logger.debug("End create closeoutCreated = {}", closeoutCreated);
        return closeoutCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public Closeout findById(@NotNull Long id) {
        logger.debug("Begin findById id = {}", id);
        Closeout closeoutFound = closeoutGateway.findById(id);
        logger.debug("End findById closeoutFound = {}", closeoutFound);
        return closeoutFound;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Closeout> findAll(@NotNull Pageable pageable) {
        logger.debug("Begin findAll, pageable = {}", pageable);
        Page<Closeout> closeoutsFound = closeoutGateway.findAll(pageable);
        return closeoutsFound;
    }

    @Override
    public Closeout update(@NotNull Long id, @NotNull CloseoutSaveCmd closeoutToUpdateCmd) {
        logger.debug("Begin update id = {}, closeoutToUpdateCmd = {}", id, closeoutToUpdateCmd);
        Closeout closeoutInDataBase = findById(id);
        Closeout closeoutToUpdate = closeoutInDataBase.toBuilder()
            .price(closeoutToUpdateCmd.getPrice())
            .build();
        Closeout closeoutUpdated = closeoutGateway.update(closeoutToUpdate);
        logger.debug("End update closeoutUpdated = {}", closeoutUpdated);
        return closeoutUpdated;
    }
}
