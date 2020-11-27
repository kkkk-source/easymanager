package co.udea.ede.easymanager.role.modelo;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "ROLE")
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Que hace esto
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @NotBlank
    @Column
    @Size(min = 5, max = 200)
    private String description;
}