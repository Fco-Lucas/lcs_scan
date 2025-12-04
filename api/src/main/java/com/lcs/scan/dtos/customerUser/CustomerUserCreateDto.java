package com.lcs.scan.dtos.customerUser;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerUserCreateDto {
    @NotBlank(message = "O campo 'login' é obrigatório")
    private String login;
    @NotBlank(message = "O campo 'password' é obrigatório")
    @Size(min = 6, message = "O campo 'password' deve conter ao menos 6 caracteres")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&.,;:_+=(){}\\[\\]<>^#|-]).{6,}$",
            message = "A senha deve conter ao menos 6 caracteres, incluindo uma letra maiúscula, uma letra minúscula, um número e um caractere especial."
    )
    private String password;

    public CustomerUserCreateDto() {
    }

    public CustomerUserCreateDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

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

    @Override
    public String toString() {
        return "CustomerUserCreateDto{" +
                "login=" + login +
                ", password='" + password + '\'' +
                '}';
    }
}
