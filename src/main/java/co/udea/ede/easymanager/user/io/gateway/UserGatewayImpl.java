package co.udea.ede.easymanager.user.io.gateway;


import co.udea.ede.easymanager.user.io.repository.UserRepository;
import co.udea.ede.easymanager.user.model.User;
import co.udea.ede.easymanager.user.service.UserGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Repository
public class UserGatewayImpl implements UserGateway {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserRepository  userRepository;

    @Override
    public User save(@NotNull User userToCreate) {
        logger.debug("Begin save userToCreate = {}", userToCreate);

        final User userToBeCreated =
                userToCreate.toBuilder().createDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();

        final User userCreated = userRepository.save(userToBeCreated);

        logger.debug("End save userCreated = {}", userCreated);

        return userCreated;
    }
}
