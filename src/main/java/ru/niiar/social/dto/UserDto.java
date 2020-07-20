package ru.niiar.social.dto;

import ru.niiar.social.customvalidator.PasswordMatches;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@PasswordMatches
public class UserDto {
    @NotEmpty(message = "Field login must not be empty")
    @Size(min=3, message = "Login field must be at least 3 characters")
    private String login;

    @NotEmpty(message = "Field password must not be empty")
    @Size(min=6, message = "Password field must be at least 6 characters")
    private String password;

    @NotEmpty(message = "Field confirm-password must not be empty")
    @Size(min=6, message = "Password field must be at least 6 characters")
    private String matchingPassword;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
