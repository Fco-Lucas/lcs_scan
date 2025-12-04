package com.lcs.scan.dtos.systemUser;

public class SystemUserUpdateDto {
    private String name;
    private String email;

    public SystemUserUpdateDto() {
    }

    public SystemUserUpdateDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SystemUserUpdateDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
