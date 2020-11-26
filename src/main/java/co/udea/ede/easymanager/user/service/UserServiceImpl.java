package co.udea.ede.easymanager.user.service;

import co.udea.ede.easymanager.user.model.User;
import co.udea.ede.easymanager.user.service.model.UserSaveCmd;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public User create(UserSaveCmd userToCreateCmd) {
        return null;
    }

}
