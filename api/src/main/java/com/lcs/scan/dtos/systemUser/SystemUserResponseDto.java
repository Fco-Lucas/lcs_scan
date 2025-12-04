package com.lcs.scan.dtos.systemUser;

import com.lcs.scan.enums.systemUser.SystemUserStatus;

import java.time.LocalDateTime;

public class SystemUserResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private String name;
    private String email;
    private SystemUserStatus status;

    public SystemUserResponseDto() {
    }

    public SystemUserResponseDto(Long id, LocalDateTime createdAt, String name, String email, SystemUserStatus status) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public SystemUserStatus getStatus() {
        return status;
    }

    public void setStatus(SystemUserStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SystemUserResponseDto{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}
