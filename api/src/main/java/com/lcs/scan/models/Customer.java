package com.lcs.scan.models;

import com.lcs.scan.enums.customer.CustomerStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false, name = "created_at", columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime createdAt;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(name = "cpf_cnpj", nullable = false)
    private String cpfCnpj;
    @Column(name = "id_plan", nullable = false)
    private Long idPlan;
    @Column(name = "url_to_post")
    private String urlToPost;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 12)
    private CustomerStatus status;

    public Customer() {
    }

    public Customer(Long id, LocalDateTime createdAt, String name, String cpfCnpj, Long idPlan, String urlToPost, CustomerStatus status) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.cpfCnpj = cpfCnpj;
        this.idPlan = idPlan;
        this.urlToPost = urlToPost;
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

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Long getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Long idPlan) {
        this.idPlan = idPlan;
    }

    public String getUrlToPost() {
        return urlToPost;
    }

    public void setUrlToPost(String urlToPost) {
        this.urlToPost = urlToPost;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", name='" + name + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", idPlan=" + idPlan +
                ", urlToPost='" + urlToPost + '\'' +
                ", status=" + status +
                '}';
    }
}
