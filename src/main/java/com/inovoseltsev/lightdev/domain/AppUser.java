package com.inovoseltsev.lightdev.domain;


import com.inovoseltsev.lightdev.role.Role;
import com.inovoseltsev.lightdev.state.State;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "app_user")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name", nullable = false)
    @NonNull
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NonNull
    private String lastName;

    @Column(name = "login", nullable = false, unique = true)
    @NonNull
    private String login;

    @Column(name = "password", nullable = false)
    @NonNull
    private String password;

    @Column(name = "email", unique = true)
    @NonNull
    private String email;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NonNull
    private Role role;

    @Column(name = "state", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NonNull
    private State state;
}
