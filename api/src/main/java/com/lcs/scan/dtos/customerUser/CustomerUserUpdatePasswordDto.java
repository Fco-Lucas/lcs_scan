package com.lcs.scan.dtos.customerUser;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerUserUpdatePasswordDto {
    @NotBlank(message = "O campo 'currentPassword' é obrigatório")
    @Size(min = 6, message = "O campo 'currentPassword' deve conter ao menos 6 caracteres")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&.,;:_+=(){}\\[\\]<>^#|-]).{6,}$",
            message = "A senha atual deve conter ao menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial"
    )
    private String currentPassword;
    @NotBlank(message = "O campo 'newPassword' é obrigatório")
    @Size(min = 6, message = "O campo 'newPassword' deve conter ao menos 6 caracteres")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&.,;:_+=(){}\\[\\]<>^#|-]).{6,}$",
            message = "A nova senha deve conter ao menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial"
    )
    private String newPassword;
    @NotBlank(message = "O campo 'confirmNewPassword' é obrigatório")
    @Size(min = 6, message = "O campo 'confirmNewPassword' deve conter ao menos 6 caracteres")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&.,;:_+=(){}\\[\\]<>^#|-]).{6,}$",
            message = "A confirmação da nova senha deve conter ao menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial"
    )
    private String confirmNewPassword;

    public CustomerUserUpdatePasswordDto() {
    }

    public CustomerUserUpdatePasswordDto(String currentPassword, String newPassword, String confirmNewPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    @Override
    public String toString() {
        return "CustomerUserUpdatePasswordDto{" +
                "currentPassword='" + currentPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmNewPassword='" + confirmNewPassword + '\'' +
                '}';
    }
}
