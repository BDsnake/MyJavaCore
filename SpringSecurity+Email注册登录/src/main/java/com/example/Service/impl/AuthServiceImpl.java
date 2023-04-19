package com.example.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.Entity.SysUser;
import com.example.Service.IAuthService;
import com.example.dao.AuthDao;
import com.example.result.Result;
import com.example.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AuthServiceImpl extends ServiceImpl<AuthDao,SysUser> implements IAuthService {
//    @Lazy
    @Resource
    BCryptPasswordEncoder passwordEncoder;
    @Resource
    RedisUtils redisUtils;
    public boolean addUser(SysUser sysUser) {
        System.out.println(passwordEncoder.encode(sysUser.getPassword()));
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUser.setUserRole("ROLE_USER");
        sysUser.setUserLocked(0);
        save(sysUser);
        return true;
    }

    @Override
    public int register(String username, String password, String email, String code) {
        String value = redisUtils.get(email).toString();
        String a = null;
        if(value==null){
            log.info("邮箱  "+email+"  的验证码不存在");
            return -1;
        }
        if(!code.equals(value)){
            log.info("邮箱  "+email+"  验证码错误");
            return -2;
        }
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUser.setEmail(email);
        sysUser.setUserLocked(0);
        sysUser.setUserRole("ROLE_USER");
        try {
            save(sysUser);
        } catch (Exception e){
            log.error(e.getMessage());
            return -3;
        }
        return 1;
    }
}
