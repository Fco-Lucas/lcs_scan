package com.lcs.scan.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "system_user_roles")
public class SystemUserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "system_user_id")
    private Long systemUserId;
    @Column(nullable = false, name = "role_id")
    private Long roleId;

    public SystemUserRole() {
    }

    public SystemUserRole(Long id, Long systemUserId, Long roleId) {
        this.id = id;
        this.systemUserId = systemUserId;
        this.roleId = roleId;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SystemUserRole that = (SystemUserRole) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SystemUserRole{" +
                "id=" + id +
                ", systemUserId=" + systemUserId +
                ", roleId=" + roleId +
                '}';
    }
}
