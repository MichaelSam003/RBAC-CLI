package com.sam.rbac.service;

import com.alibaba.fastjson.JSONArray;
import com.sam.rbac.base.result.Results;
import com.sam.rbac.model.SysPermission;

import java.util.List;

public interface PermissionService {

    Results<JSONArray> listAllPermission();

    Results<SysPermission> listByRoleId(Integer intValue);

    Results<SysPermission> getMenuAll();

    Results<SysPermission> save(SysPermission sysPermission);

    SysPermission getSysPermissionById(Integer id);

    Results  updateSysPermission(SysPermission sysPermission);

    Results delete(Integer id);

    List<SysPermission> getMenu();

    Results<SysPermission> getMenu(Long userId);
}
