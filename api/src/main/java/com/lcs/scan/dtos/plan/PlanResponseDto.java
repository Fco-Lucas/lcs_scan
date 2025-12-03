package com.lcs.scan.dtos.plan;

import com.lcs.scan.enums.plan.PlanStatus;

import java.time.LocalDateTime;

public class PlanResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private String name;
    private Integer imagesAvailable;
    private String observations;
    private PlanStatus status;

    public PlanResponseDto() {
    }

    public PlanResponseDto(Long id, LocalDateTime createdAt, String name, Integer imagesAvailable, String observations, PlanStatus status) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.imagesAvailable = imagesAvailable;
        this.observations = observations;
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

    public PlanStatus getStatus() {
        return status;
    }

    public void setStatus(PlanStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PlanResponseDto{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", name='" + name + '\'' +
                ", imagesAvailable=" + imagesAvailable +
                ", observations='" + observations + '\'' +
                ", status=" + status +
                '}';
    }
}
