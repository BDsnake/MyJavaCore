package com.example.Service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.Entity.SysUser;
import com.example.Service.IAuthService;
import com.example.dao.AuthDao;
import com.example.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author BDsnake
 * @since 2023/4/14 9:26
 */
@Service
public class AuthServiceImpl extends ServiceImpl<AuthDao,SysUser> implements IAuthService {
//    @Lazy
    @Resource
    BCryptPasswordEncoder passwordEncoder;
    @Override
    public boolean addUser(SysUser sysUser) {
        System.out.println(passwordEncoder.encode(sysUser.getPassword()));
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUser.setUserRole("ROLE_USER");
        sysUser.setUserLocked(0);
        save(sysUser);
        return true;
    }
}
