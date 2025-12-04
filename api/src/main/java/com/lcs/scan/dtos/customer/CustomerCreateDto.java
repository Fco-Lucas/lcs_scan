package com.lcs.scan.dtos.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CustomerCreateDto {
    @NotBlank(message = "O campo 'name' é obrigatório")
    private String name;
    @NotBlank(message = "O campo 'cpfCnpj' é obrigatório")
    private String cpfCnpj;
    @NotNull(message = "O campo 'idPlan' é obrigatório")
    private Long idPlan;
    private String urlToPost;

    public CustomerCreateDto() {
    }

    public CustomerCreateDto(String name, String cpfCnpj, Long idPlan, String urlToPost) {
        this.name = name;
        this.cpfCnpj = cpfCnpj;
        this.idPlan = idPlan;
        this.urlToPost = urlToPost;
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

    @Override
    public String toString() {
        return "CustomerCreateDto{" +
                "name='" + name + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", idPlan=" + idPlan +
                ", urlToPost='" + urlToPost + '\'' +
                '}';
    }
}
