package co.edu.udea.easymanager.closeout.service;

import co.edu.udea.easymanager.closeout.model.Closeout;
import co.edu.udea.easymanager.closeout.service.model.CloseoutSaveCmd;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface CloseoutService {

    Closeout create(@NotNull CloseoutSaveCmd closeoutToCreateCmd);

    Closeout findById(@NotNull Long id);

    Page<Closeout> findAll(@NotNull Pageable pageable);

    Closeout update(@NotNull Long id, @NotNull CloseoutSaveCmd closeoutToUpdateCmd);
}
