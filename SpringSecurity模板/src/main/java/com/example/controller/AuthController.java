package com.example.controller;

import com.example.Entity.SysUser;
import com.example.Service.IAuthService;
import com.example.result.Result;
import com.example.result.ResultEnum;
import com.example.result.ResultGenerator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author BDsnake
 * @since 2023/4/14 9:01
 */
@RestController
@CrossOrigin
public class AuthController {
    @Resource
    IAuthService authService;
    @PostMapping("/api/auth/register")
    public Result<String> register(String username,String password){
        if(username==null||password==null) return ResultGenerator.error(ResultEnum.INVALID_REQUEST);
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        boolean save = authService.addUser(sysUser);
        if(save) return ResultGenerator.success(ResultEnum.OK);
        else return ResultGenerator.error(ResultEnum.INTERNAL_SERVER_ERROR);
    }
}
