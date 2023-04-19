package com.example.Service;

import com.example.dto.EmailDto;

/**
 * @author BDsnake
 * @since 2023/4/18 9:02
 */
public interface IEmailService {
    void send(EmailDto emailDto);
    void sendEmailCode(String to);
}
