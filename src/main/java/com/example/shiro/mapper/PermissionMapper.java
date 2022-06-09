package com.example.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shiro.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据角色id查询权限
     * @param roleId
     * @return
     */
    @Select("select p.id, p.name, p.url from t_permission p left join t_role_permission rp on p.id = rp.permission_id where rp.role_id = #{roleId}")
    List<Permission> getPermissionsByRoleId(Integer roleId);
}
