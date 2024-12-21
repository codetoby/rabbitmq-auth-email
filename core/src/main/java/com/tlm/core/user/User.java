package com.tlm.core.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String username;

    @JsonIgnore
    private String password;
    private boolean isVerified;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.isVerified = false;
    }

    public User(Long id, String email, String username, boolean isVerified) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.isVerified = isVerified;
    }

}
