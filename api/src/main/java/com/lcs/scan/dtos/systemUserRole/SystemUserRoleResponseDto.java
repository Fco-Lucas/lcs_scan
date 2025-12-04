package com.lcs.scan.dtos.systemUserRole;

public class SystemUserRoleResponseDto {
    private Long id;
    private Long systemUserId;
    private Long roleId;

    public SystemUserRoleResponseDto() {
    }

    public SystemUserRoleResponseDto(Long id, Long systemUserId, Long roleId) {
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
    public String toString() {
        return "SystemUserRoleResponseDto{" +
                "id=" + id +
                ", systemUserId=" + systemUserId +
                ", roleId=" + roleId +
                '}';
    }
}
