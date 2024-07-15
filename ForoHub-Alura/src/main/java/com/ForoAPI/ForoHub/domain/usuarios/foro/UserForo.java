package com.ForoAPI.ForoHub.domain.usuarios.foro;

import jakarta.persistence.*;
import lombok.*;
@Entity(name = "userForo")
@Table(name = "usersForo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserForo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String userName;
    private String password;


}
