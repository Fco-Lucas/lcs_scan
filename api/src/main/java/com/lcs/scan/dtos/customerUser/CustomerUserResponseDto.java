package com.lcs.scan.dtos.customerUser;

import com.lcs.scan.enums.customerUser.CustomerUserStatus;

import java.time.LocalDateTime;

public class CustomerUserResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private Long idCustomer;
    private String login;
    private CustomerUserStatus status;

    public CustomerUserResponseDto() {
    }

    public CustomerUserResponseDto(Long id, LocalDateTime createdAt, Long idCustomer, String login, CustomerUserStatus status) {
        this.id = id;
        this.createdAt = createdAt;
        this.idCustomer = idCustomer;
        this.login = login;
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

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public CustomerUserStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerUserStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CustomerUserResponseDto{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", idCustomer=" + idCustomer +
                ", login='" + login + '\'' +
                ", status=" + status +
                '}';
    }
}
