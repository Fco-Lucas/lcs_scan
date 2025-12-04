package com.lcs.scan.models;

import com.lcs.scan.enums.customerUser.CustomerUserStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "customer_users")
public class CustomerUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false, name = "created_at", columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime createdAt;
    @Column(nullable = false, name = "id_customer")
    private Long idCustomer;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerUserStatus status;

    public CustomerUser() {
    }

    public CustomerUser(Long id, LocalDateTime createdAt, Long idCustomer, String login, String password, CustomerUserStatus status) {
        this.id = id;
        this.createdAt = createdAt;
        this.idCustomer = idCustomer;
        this.login = login;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerUserStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerUserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CustomerUser that = (CustomerUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CustomerUser{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", idCustomer=" + idCustomer +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
