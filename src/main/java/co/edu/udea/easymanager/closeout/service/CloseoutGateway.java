package co.edu.udea.easymanager.closeout.service;

import co.edu.udea.easymanager.closeout.model.Closeout;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface CloseoutGateway {

    Closeout save(@NotNull Closeout closeoutToCreate);

    Closeout findById(@NotNull Long id);

    Page<Closeout> findAll(@NotNull Pageable pageable);

    Closeout update(@NotNull Closeout closeoutToUpdate);
}
