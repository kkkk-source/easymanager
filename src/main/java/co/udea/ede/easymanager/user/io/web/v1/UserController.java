package co.udea.ede.easymanager.user.io.web.v1;

import co.udea.ede.easymanager.shared.model.ErrorDetails;
import co.udea.ede.easymanager.user.io.web.v1.model.UserSaveRequest;
import co.udea.ede.easymanager.user.model.User;
import co.udea.ede.easymanager.user.service.UserService;
import co.udea.ede.easymanager.user.service.model.UserSaveCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping(path = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"Users"}, value = "Users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass()); // No se para que es

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    // No se para que es
    @PostMapping
    @ApiOperation(value = "Create an User.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created."),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorDetails.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorDetails.class)
    })

    @ResponseStatus(value = HttpStatus.CREATED)
    @CrossOrigin(exposedHeaders = {HttpHeaders.LOCATION})
    public ResponseEntity<Void> create(@Valid @NotNull @RequestBody UserSaveRequest userToCreate) {
        logger.debug("Begin create: userToCreate = {}", userToCreate);

        UserSaveCmd userToCreateCmd = UserSaveRequest.toModel(userToCreate);

        User userCreated = userService.create(userToCreateCmd);

        //Como funciona esto de URI
        URI location = fromUriString("/api/v1/users").path("/{id}")
                .buildAndExpand(userCreated.getId()).toUri();

        logger.debug("End create: userCreated = {}", userCreated);

        return ResponseEntity.created(location).build();
    }




}
