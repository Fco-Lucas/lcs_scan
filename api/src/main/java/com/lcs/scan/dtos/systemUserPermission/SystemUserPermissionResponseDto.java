package com.lcs.scan.dtos.systemUserPermission;

public class SystemUserPermissionResponseDto {
    private Long id;
    private Long systemUserId;
    private Long permissionId;
    private Boolean granted;

    public SystemUserPermissionResponseDto() {
    }

    public SystemUserPermissionResponseDto(Long id, Long systemUserId, Long permissionId, Boolean granted) {
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
    public String toString() {
        return "SystemUserPermissionResponseDto{" +
                "id=" + id +
                ", systemUserId=" + systemUserId +
                ", permissionId=" + permissionId +
                ", granted=" + granted +
                '}';
    }
}
