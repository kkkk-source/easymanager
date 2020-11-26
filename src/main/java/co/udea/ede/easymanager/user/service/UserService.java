package co.udea.ede.easymanager.user.service;

import co.udea.ede.easymanager.user.model.User;
import co.udea.ede.easymanager.user.service.model.UserSaveCmd;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface UserService {

    User create(@NotNull UserSaveCmd userToCreateCmd);

}