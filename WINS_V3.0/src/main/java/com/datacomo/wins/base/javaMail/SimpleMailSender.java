package com.datacomo.wins.base.javaMail;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

/**
 * 简单邮件（不带附件的邮件）发送器
 * Created by yangxiongbin on 2016/9/18.
 */
public class SimpleMailSender {
    /**
     * 以文本格式发送邮件
     * @param emailInfo
     * @return
     */
    public boolean sendTextMail(EmailInfo emailInfo){
        // 判断是否需要身份认证
        MyAuthenticator authenticator = null;
        Properties properties = emailInfo.getProperties();
        if(emailInfo.isValidate()){
            //如果需要身份认证，则创建一个密码验证器
            authenticator = new MyAuthenticator(emailInfo.getUserName(),emailInfo.getPassword());
        }
        try {
            //根据邮件会话属性和密码验证构造一个发送邮件的session
            Session sendMailSession = Session.getDefaultInstance(properties,authenticator);
            //根据Session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            //创建邮件发送者地址
            Address from =new InternetAddress(emailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(emailInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO,to);
            // 设置邮件消息的主题
            mailMessage.setSubject(emailInfo.getSubject());
            //设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            String mailContent = emailInfo.getContent();

            mailMessage.setText(mailContent);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean sendHtmlMail(EmailInfo emailInfo ){
        // 判断是否需要身份认证
        MyAuthenticator authenticator = null;
        Properties properties = emailInfo.getProperties();
        if(emailInfo.isValidate()){
            //如果需要身份认证，则创建一个密码验证器
            authenticator = new MyAuthenticator(emailInfo.getUserName(),emailInfo.getPassword());
        }
        try {
            //根据邮件会话属性和密码验证构造一个发送邮件的session
            Session sendMailSession = Session.getDefaultInstance(properties,authenticator);
            //根据Session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            //创建邮件发送者地址
            Address from =new InternetAddress(emailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(emailInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO,to);
            // 设置邮件消息的主题
            mailMessage.setSubject(emailInfo.getSubject());
            //设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart  = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(emailInfo.getContent(),"text/html;charset=utf-8");
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
