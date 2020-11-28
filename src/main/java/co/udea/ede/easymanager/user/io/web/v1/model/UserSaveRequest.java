package co.udea.ede.easymanager.user.io.web.v1.model;

import co.udea.ede.easymanager.user.model.User;
import co.udea.ede.easymanager.user.service.model.UserSaveCmd;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSaveRequest {


    private String firstName;

    private String lastName;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 45)
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 100)
    private String email;

    private String numberPhone;

    private String typeDocument;

    private String numDocument;

    public static UserSaveCmd toModel(@NotNull UserSaveRequest userToCreate) {
        return UserSaveCmd.builder()
                .firstName(userToCreate.getFirstName())
                .lastName(userToCreate.getLastName())
                .password(userToCreate.getPassword())
                .email(userToCreate.getEmail())
                .numberPhone(userToCreate.getNumberPhone())
                .typeDocument(userToCreate.getTypeDocument())
                .numDocument(userToCreate.getTypeDocument()).build();
    }

}