package com.example.Service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.example.Service.IEmailService;
import com.example.dto.EmailDto;
import com.example.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author BDsnake
 * @since 2023/4/18 9:02
 */
@Service
public class EmailServiceImpl implements IEmailService {
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private String port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Resource
    RedisUtils redisUtils;
    @Override
    public void send(EmailDto emailDto) {
        // 读取邮箱配置
        if (host == null || port == null || username == null || password == null) {
            throw new RuntimeException("邮箱配置异常");
        }
        MailAccount account = new MailAccount();
        account.setHost(host)
                .setFrom(username)
                .setUser(username)
                .setPass(password)
                .setAuth(true)
                //ssl发送
                .setSslEnable(true)
                //使用安全连接
                .setStarttlsEnable(true);
        try {
            int size = emailDto.getTos().size();
            Mail.create(account)
                    .setTos(emailDto.getTos().toArray(new String[size]))
                    .setTitle(emailDto.getSubject())
                    .setContent(emailDto.getContent())
                    .setHtml(true)
                    //关闭session
                    .setUseGlobalSession(false)
                    .send();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
    public void sendEmailCode(String to) {

        // 获取发送邮箱验证码的HTML模板
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email-code.ftl");
        String code = String.valueOf((int) (Math.random() * 100000));
        redisUtils.set(to,code,120L);
        // 发送验证码
        send(new EmailDto(Collections.singletonList(to),
                "邮箱验证码", template.render(Dict.create().set("code", code))));

    }

}
