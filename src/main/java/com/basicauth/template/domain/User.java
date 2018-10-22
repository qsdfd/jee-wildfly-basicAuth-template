package com.basicauth.template.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 100)
    private String firstname;

    @Column(nullable = false, length = 100)
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 100)
    private String lastname;

    @Column(nullable = false, length = 200)
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 200)
    private String password;

    @Column(nullable = false, length = 200)
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 200)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String firstname, String lastname, String password, String email, Role role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
