package com.example;

import com.example.Service.IEmailService;
import com.example.dto.EmailDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BDsnake
 * @since 2023/4/18 9:19
 */
@SpringBootTest
public class EmailTest {
    @Resource
    IEmailService emailService;
    @Test
    public void emailSendTest(){
        EmailDto emailDto = new EmailDto();
        List<String> tos = new ArrayList<>();
        tos.add("295935489@qq.com");
        emailDto.setTos(tos);
        emailDto.setSubject("测试");
        emailDto.setContent("测试内容");
//        emailService.send(emailDto);
        emailService.sendEmailCode("295935489@qq.com");
    }

}
