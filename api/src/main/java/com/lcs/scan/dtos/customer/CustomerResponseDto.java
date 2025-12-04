package com.lcs.scan.dtos.customer;

import com.lcs.scan.enums.customer.CustomerStatus;

import java.time.LocalDateTime;

public class CustomerResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private String name;
    private String cpfCnpj;
    private Long idPlan;
    private String urlToPost;
    private CustomerStatus status;

    public CustomerResponseDto() {
    }

    public CustomerResponseDto(Long id, LocalDateTime createdAt, String name, String cpfCnpj, Long idPlan, String urlToPost, CustomerStatus status) {
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
    public String toString() {
        return "CustomerResponseDto{" +
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
