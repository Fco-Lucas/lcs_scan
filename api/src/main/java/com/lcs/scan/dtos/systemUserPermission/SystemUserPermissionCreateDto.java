package com.lcs.scan.dtos.systemUserPermission;

public class SystemUserPermissionCreateDto {
    private Long systemUserId;
    private Long permissionId;
    private Boolean granted;

    public SystemUserPermissionCreateDto() {
    }

    public SystemUserPermissionCreateDto(Long systemUserId, Long permissionId, Boolean granted) {
        this.systemUserId = systemUserId;
        this.permissionId = permissionId;
        this.granted = granted;
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
    public String toString() {
        return "SystemUserPermissionCreateDto{" +
                "systemUserId=" + systemUserId +
                ", permissionId=" + permissionId +
                ", granted=" + granted +
                '}';
    }
}
