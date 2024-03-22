package org.launchcode.codecallers.models;

import jakarta.persistence.Access;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import javax.swing.*;
import java.util.Objects;

@Entity
public class User extends AbstractEntity {

    @NotBlank
    @Size(min=1, max=500)
    private String firstName;

    @NotBlank
    @Size(min=1, max=500)
    private String lastName;

    @NotBlank
    @Size(min=1, max=500)
    private String username;

    @NotBlank
    @Size (min=1,max=500)
    @Email
    private String email;

    @NotBlank
    @Size (min=8)
    private String password;

    private int score;


    public User(String firstName, String lastName, String username, String email, String password, int score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.score = score;
    }

    public User(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }



    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email);
    }
}
