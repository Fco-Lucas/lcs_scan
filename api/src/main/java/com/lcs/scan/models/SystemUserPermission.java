package com.lcs.scan.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "system_user_permissions")
public class SystemUserPermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "system_user_id")
    private Long systemUserId;
    @Column(nullable = false, name = "permission_id")
    private Long permissionId;
    @Column(nullable = false)
    private Boolean granted;

    public SystemUserPermission() {
    }

    public SystemUserPermission(Long id, Long systemUserId, Long permissionId, Boolean granted) {
        this.id = id;
        this.systemUserId = systemUserId;
        this.permissionId = permissionId;
        this.granted = granted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(Long systemUserId) {
        this.systemUserId = systemUserId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Boolean getGranted() {
        return granted;
    }

    public void setGranted(Boolean granted) {
        this.granted = granted;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SystemUserPermission that = (SystemUserPermission) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SystemUserPermission{" +
                "id=" + id +
                ", systemUserId=" + systemUserId +
                ", permissionId=" + permissionId +
                ", granted=" + granted +
                '}';
    }
}
