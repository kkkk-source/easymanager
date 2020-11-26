package co.udea.ede.easymanager.user.service.model;

import co.udea.ede.easymanager.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSaveCmd {


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

    public static User toModel(@NotNull UserSaveCmd userToCreateCmd) {
        return User.builder()
                .firsName(userToCreateCmd.getFirstName())
                .lastName(userToCreateCmd.getLastName())
                .passWord(userToCreateCmd.getPassword())
                .email(userToCreateCmd.getEmail())
                .numberPhone(userToCreateCmd.getNumberPhone())
                .typeDocument(userToCreateCmd.getTypeDocument())
                .numDocument(userToCreateCmd.getTypeDocument()).build();
    }

}
