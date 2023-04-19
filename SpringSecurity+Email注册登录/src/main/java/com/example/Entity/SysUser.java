package com.example.Entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author BDsnake
 * @since 2023/4/14 8:44
 */
@Data
public class SysUser {
    @TableId(type = IdType.ASSIGN_UUID)
    String userId;
    String username;
    String password;
    @Email
    String email;
    String userRole;
    Integer userLocked;
    @TableField(fill = FieldFill.INSERT)
    LocalDateTime createTime;
}
