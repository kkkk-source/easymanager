package co.udea.ede.easymanager.user.service;

import co.udea.ede.easymanager.user.model.User;
import co.udea.ede.easymanager.user.service.model.UserSaveCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class UserServiceImpl implements UserService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserGateway userGateway;

    public UserServiceImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public User create(@NotNull UserSaveCmd userToCreateCmd) {
        logger.debug("Begin create userToCreateCmd = {}", userToCreateCmd);

        User userToCreate = UserSaveCmd.toModel(userToCreateCmd);

        userToCreate.setPassword(userToCreateCmd.getPassword());

        User userCreated = userGateway.save(userToCreate);

        logger.debug("End create userCreated = {}", userCreated);

        return userCreated;
    }
}
