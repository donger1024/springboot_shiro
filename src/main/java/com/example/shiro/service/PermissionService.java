package com.example.shiro.service;

import com.example.shiro.pojo.Permission;

import java.util.List;

public interface PermissionService {

    /**
     * 根据角色id获取权限集合
     * @param roleId
     * @return
     */
    List<Permission> getPermissionsByRoleId(Integer roleId);
}
