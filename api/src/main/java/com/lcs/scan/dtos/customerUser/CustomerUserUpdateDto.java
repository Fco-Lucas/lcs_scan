package com.lcs.scan.dtos.customerUser;

public class CustomerUserUpdateDto {
    private String login;

    public CustomerUserUpdateDto() {
    }

    public CustomerUserUpdateDto(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "CustomerUserUpdateDto{" +
                "login='" + login + '\'' +
                '}';
    }
}
