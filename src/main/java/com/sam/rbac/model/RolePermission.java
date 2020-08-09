package com.sam.rbac.model;

import lombok.Data;

@Data
public class RolePermission {
    private Integer roleId;
    private Integer permissionId;
}
