package com.lcs.scan.dtos.plan;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlanCreateDto {
    @NotBlank(message = "O campo 'name' é obrigatório")
    private String name;
    @NotNull(message = "O campo 'imagesAvailable' é obrigatório")
    private Integer imagesAvailable;
    @NotBlank(message = "O campo 'observations' é obrigatório")
    private String observations;

    public PlanCreateDto() {
    }

    public PlanCreateDto(String name, Integer imagesAvailable, String observations) {
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
        return "CreatePlanDto{" +
                "name='" + name + '\'' +
                ", imagesAvailable=" + imagesAvailable +
                ", observations='" + observations + '\'' +
                '}';
    }
}
