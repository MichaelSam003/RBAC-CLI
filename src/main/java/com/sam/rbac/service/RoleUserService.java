package com.sam.rbac.service;


import com.sam.rbac.base.result.Results;

public interface RoleUserService {

    Results getSysRoleUserByUserId(Integer userId);

}
