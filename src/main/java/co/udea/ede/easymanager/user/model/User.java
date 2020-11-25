package co.udea.ede.easymanager.user.model;


import co.udea.ede.easymanager.role.modelo.Role;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "USUARIO")
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String firsName;
    @Column
    private String lastName;
    @Column
    private String passWord;
    @Column
    private String email;
    @Column
    private String numberPhone;
    @Column
    private String typeDocument;
    @Column
    private String numDocument;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (
            name = "user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Set<Role> roles; //La diferencia a usar un set es que obligamos a que no se repita ningun valor

}
