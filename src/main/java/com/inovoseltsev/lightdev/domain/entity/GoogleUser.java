package com.inovoseltsev.lightdev.domain.entity;

import com.inovoseltsev.lightdev.domain.state.State;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "google_user")
public class GoogleUser extends AbstractEntity {

    @Column(name = "google_user_id")
    @NonNull
    private String googleUserId;

    @Column(name = "name")
    @NonNull
    private String nickname;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @Column(name = "email", unique = true)
    @NonNull
    private String email;

    @Column(name = "locale")
    @NonNull
    private String locale;

    public GoogleUser(Map<String, Object> userAttributes) {
        this.googleUserId = (String) userAttributes.get("sub");
        this.nickname = (String) userAttributes.get("name");
        this.email = (String) userAttributes.get("email");
        this.locale = (String) userAttributes.get("locale");
        this.firstName = "";
        this.lastName = "";
        this.setState(State.ACTIVE);
    }
}
