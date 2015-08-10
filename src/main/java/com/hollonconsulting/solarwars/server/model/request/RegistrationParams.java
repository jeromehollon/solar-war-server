package com.hollonconsulting.solarwars.server.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Player")
@XmlAccessorType(value = XmlAccessType.PROPERTY)
public class RegistrationParams {
    @NotNull(message = "Username is required.")
    @Size(min=5, max=255, message = "Username must be between 5 and 255 characters")
    private String username;

    @NotNull(message = "Password is required.")
    @Size(min=8, max=4096, message = "Password must be between 8 and 4096 characters")
    private String password;

    private String email;

    public RegistrationParams() {
    }

    public RegistrationParams(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
