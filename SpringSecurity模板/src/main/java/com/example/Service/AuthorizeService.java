package com.example.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.Entity.SysUser;
import com.example.dao.AuthDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BDsnake
 * @since 2023/4/12 21:01
 */
@Service
public class AuthorizeService implements UserDetailsService {
    @Resource
    AuthDao authDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = authDao.selectOne(new QueryWrapper<SysUser>().eq("username",username));
        if(sysUser==null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if(sysUser.getUserLocked()==1){
            throw new UsernameNotFoundException("账号已封禁");
        }
        String role = sysUser.getUserRole();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
    }
}
