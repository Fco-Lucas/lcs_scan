package com.lcs.scan.dtos.plan;

public class PlanUpdateDto {
    private String name;
    private Integer imagesAvailable;
    private String observations;

    public PlanUpdateDto() {
    }

    public PlanUpdateDto(String name, Integer imagesAvailable, String observations) {
        this.name = name;
        this.imagesAvailable = imagesAvailable;
        this.observations = observations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImagesAvailable() {
        return imagesAvailable;
    }

    public void setImagesAvailable(Integer imagesAvailable) {
        this.imagesAvailable = imagesAvailable;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        return "PlanUpdateDto{" +
                "name='" + name + '\'' +
                ", imagesAvailable=" + imagesAvailable +
                ", observations='" + observations + '\'' +
                '}';
    }
}
