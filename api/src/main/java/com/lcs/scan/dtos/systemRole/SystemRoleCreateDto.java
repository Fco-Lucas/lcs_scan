package com.lcs.scan.dtos.systemRole;

import jakarta.validation.constraints.NotBlank;

public class SystemRoleCreateDto {
    @NotBlank(message = "O campo 'name' é obrigatório")
    private String name;
    private String description;

    public SystemRoleCreateDto() {
    }

    public SystemRoleCreateDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SystemRoleCreateDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
