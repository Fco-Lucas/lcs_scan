package com.lcs.scan.models;

import com.lcs.scan.enums.plan.PlanStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "plans")
public class Plan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false, name = "created_at", columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, name = "images_available")
    private Integer imagesAvailable;
    private String observations;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanStatus status;

    public Plan() {
    }

    public Plan(Long id, LocalDateTime createdAt, String name, Integer imagesAvailable, String observations, PlanStatus status) {
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return Objects.equals(id, plan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", name='" + name + '\'' +
                ", imagesAvailable=" + imagesAvailable +
                ", observations='" + observations + '\'' +
                ", status=" + status +
                '}';
    }
}
