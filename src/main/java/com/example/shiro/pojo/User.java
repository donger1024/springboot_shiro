package com.example.shiro.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
@ApiModel("用户实体类")
@Repository
public class User implements Serializable {
    /** 数据库中设置该字段自增时该注解不能少 **/
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name = "id", value = "ID 主键")
    private Integer id;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(name = "username", value = "用户名")
    private String username;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(name = "password", value = "密码")
    private String password;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "salt", value = "盐")
    private String salt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(name = "age", value = "年龄")
    private Integer age;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(name = "email", value = "邮箱")
    private String email;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(name = "address", value = "地址")
    private String address;
}
