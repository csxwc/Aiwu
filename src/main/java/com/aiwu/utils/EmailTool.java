package com.aiwu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class EmailTool {

    @Autowired
    private JavaMailSenderImpl sender = new JavaMailSenderImpl();

    private String from = "congregalis@163.com";

    private Properties p = new Properties();

    public void sendCodeToEmail(String email, String code) {
        MimeMessage message = null;
        p.put("userName", "congregalis@163.com");

        try {

            message = sender.createMimeMessage();
            message.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(p.getProperty("userName")));
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(email);
            helper.setSubject("激活您的爱屋帐号");
            // 加载html
            StringBuffer sb = new StringBuffer();
            sb.append("<h1 style=\" text-align:center;\">验证码为</h1>");
            sb.append("<h2 style=\" text-align:center;\">" + code + "</h2>");
            sb.append("<h3 style=\" text-align:center;\">若非本人操作，请忽略。</h3>");
//            sb.append("<h3 style=\"color:blue;font-size=20px\">Hello World!</h3>");
//            sb.append("<h2 style=\" text-align:center;\">" + code + "</h2>");
//            sb.append("<p style=\" text-align:center;\">若非本人操作，请忽略。</p>");

            helper.setText(sb.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        sender.send(message);
    }

}
