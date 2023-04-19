package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.Entity.SysUser;
import com.example.Service.IAuthService;
import com.example.Service.IEmailService;
import com.example.result.Result;
import com.example.result.ResultEnum;
import com.example.result.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author BDsnake
 * @since 2023/4/14 9:01
 */
@RestController
@CrossOrigin
@Slf4j
public class AuthController {
    @Resource
    IAuthService authService;
    @Resource
    IEmailService emailService;
    @PostMapping("/api/auth/register")
    public Result<String> register(String username,String password,String email,String code){
        if(username==null||password==null) return ResultGenerator.error(ResultEnum.INVALID_REQUEST);
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        if(authService.getOne(new QueryWrapper<SysUser>().eq("username",username))!=null){
            log.info("用户名： "+username+"  已被注册");
            return ResultGenerator.custom(false,null,431,"用户名： "+username+"  已被注册");
        }
        if(authService.getOne(new QueryWrapper<SysUser>().eq("email",email))!=null){
            log.info("邮箱： "+email+"  已被注册");
            return ResultGenerator.custom(false,null,431,"邮箱： "+email+"  已被注册");
        }
        int register = authService.register(username, password, email, code);
        switch (register){
            case 1: return ResultGenerator.success();
            case -1: return ResultGenerator.custom(false,null,432,"邮箱  "+email+"  的验证码不存在");
            case -2: return ResultGenerator.custom(false,null,432,"邮箱  "+email+"  验证码错误");
            case -3: return ResultGenerator.error(ResultEnum.INTERNAL_SERVER_ERROR);
            default: return ResultGenerator.error(ResultEnum.UNKNOWN_ERROR);
        }
    }
    @GetMapping("/api/auth/sendCode")
    public Result<String> sendCode(String email){
        emailService.sendEmailCode(email);
        return ResultGenerator.success();
    }
}
