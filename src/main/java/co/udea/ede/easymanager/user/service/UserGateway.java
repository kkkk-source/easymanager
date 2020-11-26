package co.udea.ede.easymanager.user.service;


import co.udea.ede.easymanager.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface UserGateway {

    User save(@NotNull User userToCreate);

    /*
    User findById(@NotNull Long id);

    Page<User> findByParameters(@NotNull UserQuerySearchCmd queryCriteria, @NotNull Pageable pageable);

    void deleteById(@NotNull Long id);

    User update(@NotNull User userToUpdate);
    */

}