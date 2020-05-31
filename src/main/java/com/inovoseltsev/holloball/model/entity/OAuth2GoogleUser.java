package com.inovoseltsev.holloball.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "O_AUTH2_GOOGLE_USER")
public class OAuth2GoogleUser {
    @Id
    @Column(name = "USER_ID")
    private String id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL", unique = true)
    private String email;
    @Column(name = "LOCALE")
    private String locale;

    public OAuth2GoogleUser() {
    }

    public OAuth2GoogleUser(Map<String, Object> userAttributes) {
        this.id = (String) userAttributes.get("sub");
        this.name = (String) userAttributes.get("name");
        this.email = (String) userAttributes.get("email");
        this.locale = (String) userAttributes.get("locale");
        this.firstName = "";
        this.lastName = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firsName) {
        this.firstName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
