package com.kwizera.springbootamalitechlab09taskmanager.domain.dto;

public class UserSignInDTO {
    private String names;
    private String email;

    public UserSignInDTO(String names, String email) {
        this.names = names;
        this.email = email;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
