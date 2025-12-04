package com.lcs.scan.dtos.systemRolePermission;

public class SystemRolePermissionCreateDto {
    private Long roleId;
    private Long permissionId;

    public SystemRolePermissionCreateDto() {
    }

    public SystemRolePermissionCreateDto(Long roleId, Long permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "SystemRolePermissionCreateDto{" +
                "roleId=" + roleId +
                ", permissionId=" + permissionId +
                '}';
    }
}
