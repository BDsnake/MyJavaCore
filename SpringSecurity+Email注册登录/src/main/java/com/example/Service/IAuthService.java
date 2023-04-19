package com.example.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.Entity.SysUser;
import com.example.result.Result;

/**
 * @author BDsnake
 * @since 2023/4/14 8:49
 */
public interface IAuthService extends IService<SysUser> {
    int register(String username,String password,String email,String code);
}
