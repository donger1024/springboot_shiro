package com.example.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.example.shiro.pojo.User;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户账号查询信息
     * @param userName
     * @return
     */
    @Select("select * from t_user where username = #{userName}")
    User findUserByUsername(String userName);
}
