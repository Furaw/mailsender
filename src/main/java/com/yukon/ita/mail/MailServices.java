package com.yukon.ita.mail;

import com.yukon.ita.recipient.Recipient;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class MailServices {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration configuration;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    public MailResponse sendEmail(Recipient[] request) {

        MailResponse response = new MailResponse();
        MimeMessage message = mailSender.createMimeMessage();

        try {

            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            for (int i = 0; i <= request.length - 1; i++) {
                helper.setTo(request[i].getRecipientEmail());
                helper.setText(request[i].getRecipientText(), true);
                helper.setSubject(request[i].getRecipientSubject());
                helper.setFrom(new InternetAddress("lillinkproject@gmail.com", "MailSender"));
                mailSender.send(message);
                response.setMessage("mail send to : " + request[i].getRecipientEmail());
                response.setStatus(Boolean.TRUE);
            }

        } catch (MessagingException | UnsupportedEncodingException e) {
            response.setMessage("Mail Sending failure : " + e.getMessage());
            response.setStatus(Boolean.FALSE);
        }

        return response;
    }

    public MailResponse sendSimpleMessage(Recipient mail, Map<String,Object> model) {
        MailResponse response = new MailResponse();
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Template t = configuration.getTemplate("email_test.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(mail.getRecipientEmail());
            helper.setText(html, true);
            helper.setSubject(mail.getRecipientSubject());
            helper.setFrom(new InternetAddress("lillinkproject@gmail.com", "MailSender"));

            mailSender.send(message);

            response.setMessage("mail send to : " + mail.getRecipientEmail());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException e) {
            response.setMessage("Mail Sending failure : " + e.getMessage());
            response.setStatus(Boolean.FALSE);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

        return response;
    }
    public void setNewBean(String UserMail, String UserPassword) {
        ((JavaMailSenderImpl) mailSender).setUsername(UserMail);
        ((JavaMailSenderImpl) mailSender).setPassword(UserPassword);
    }

    public void setDefaultBean() {
        ((JavaMailSenderImpl) mailSender).setUsername(username);
        ((JavaMailSenderImpl) mailSender).setPassword(password);
    }


}
