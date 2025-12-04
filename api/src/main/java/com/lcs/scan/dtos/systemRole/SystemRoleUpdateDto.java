package com.lcs.scan.dtos.systemRole;

public class SystemRoleUpdateDto {
    private String name;
    private String description;

    public SystemRoleUpdateDto() {
    }

    public SystemRoleUpdateDto(String name, String description) {
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
        return "SystemRoleUpdateDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
