package com.example.shiro.realm;

import com.example.shiro.pojo.Permission;
import com.example.shiro.pojo.Role;
import com.example.shiro.pojo.User;
import com.example.shiro.service.PermissionService;
import com.example.shiro.service.RoleService;
import com.example.shiro.service.UserService;
import com.example.shiro.utils.ApplicationContextUtil;
import com.example.shiro.utils.CustomerByteSource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 自定义realm
 */
public class CustomerRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取主身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        //根据主身份信息获取角色信息
        UserService userService = (UserService) ApplicationContextUtil.getBean("userService");
        User user = userService.findUserByUserName(primaryPrincipal);

        RoleService roleService = (RoleService) ApplicationContextUtil.getBean("roleService");
        List<Role> roles = roleService.getRolesByUserId(user.getId());
        if (!CollectionUtils.isEmpty(roles)){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            for (Role role : roles) {
                simpleAuthorizationInfo.addRole(role.getName());
                PermissionService permissionService = (PermissionService) ApplicationContextUtil.getBean("permissionService");
                List<Permission> permissions = permissionService.getPermissionsByRoleId(role.getId());
                if (!CollectionUtils.isEmpty(permissions)){
                    for (Permission permission : permissions) {
                        simpleAuthorizationInfo.addStringPermission(permission.getName());
                    }
                }
            }
            return simpleAuthorizationInfo;
        }
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取当前登录的主题
        String principal = (String) authenticationToken.getPrincipal();
        UserService userService = (UserService) ApplicationContextUtil.getBean("userService");
        User user = userService.findUserByUserName(principal);
        if(!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), new CustomerByteSource(user.getSalt()),this.getName());
        }
        return null;
    }
}
