package com.lcs.scan.dtos.customer;

public class CustomerUpdateDto {
    private String name;
    private Long idPlan;
    private String urlToPost;

    public CustomerUpdateDto() {
    }

    public CustomerUpdateDto(String name, Long idPlan, String urlToPost) {
        this.name = name;
        this.idPlan = idPlan;
        this.urlToPost = urlToPost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "CustomerUpdateDto{" +
                "name='" + name + '\'' +
                ", idPlan=" + idPlan +
                ", urlToPost='" + urlToPost + '\'' +
                '}';
    }
}
