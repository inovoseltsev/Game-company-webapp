package com.inovoseltsev.lightdev.domain;

import com.inovoseltsev.lightdev.domain.state.State;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "O_AUTH2_GOOGLE_USER")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OAuth2GoogleUser {

    @Id
    @Column(name = "USER_ID")
    @NonNull
    private String id;

    @Column(name = "NAME")
    @NonNull
    private String nickname;

    @Column(name = "FIRST_NAME")
    @NonNull
    private String firstName;

    @Column(name = "LAST_NAME")
    @NonNull
    private String lastName;

    @Column(name = "EMAIL", unique = true)
    @NonNull
    private String email;

    @Column(name = "LOCALE")
    @NonNull
    private String locale;

    @Column(name = "STATE")
    @Enumerated(value = EnumType.STRING)
    @NonNull
    private State state;

    public OAuth2GoogleUser(Map<String, Object> userAttributes) {
        this.id = (String) userAttributes.get("sub");
        this.nickname = (String) userAttributes.get("name");
        this.email = (String) userAttributes.get("email");
        this.locale = (String) userAttributes.get("locale");
        this.firstName = "";
        this.lastName = "";
        this.state = State.ACTIVE;
    }

    public State getState() {
        if (state == null) {
            state = State.ACTIVE;
        }
        return state;
    }
}
