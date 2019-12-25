package com.yukon.ita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.yukon.ita")
public class MailSenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailSenderApplication.class, args);

    }
}
