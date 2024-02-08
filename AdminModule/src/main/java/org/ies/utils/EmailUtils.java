package org.ies.utils;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
    @Autowired
    JavaMailSender mailSender;
    public boolean sendEmail(String sub,String body ,String to) {

        boolean isSend = false;
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setTo(to);
            helper.setSubject(sub);
            helper.setText(body, true);
            mailSender.send(mimeMessage);
            isSend = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSend;
    }
}
