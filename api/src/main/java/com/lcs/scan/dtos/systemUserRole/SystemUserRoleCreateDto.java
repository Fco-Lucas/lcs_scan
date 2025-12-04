package com.lcs.scan.dtos.systemUserRole;

public class SystemUserRoleCreateDto {
    private Long systemUserId;
    private Long roleId;

    public SystemUserRoleCreateDto() {
    }

    public SystemUserRoleCreateDto(Long systemUserId, Long roleId) {
        this.systemUserId = systemUserId;
        this.roleId = roleId;
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
        return "SystemUserRoleCreateDto{" +
                "systemUserId=" + systemUserId +
                ", roleId=" + roleId +
                '}';
    }
}
