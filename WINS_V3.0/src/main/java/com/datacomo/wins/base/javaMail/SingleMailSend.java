package com.datacomo.wins.base.javaMail;
import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
/**
 * Created by yangxiongbin on 2016/9/19.
 */
public class SingleMailSend {
    public static void main(String args[])
    {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        // 设定mail server
        senderImpl.setHost("smtp.163.com");

        // 建立邮件消息
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人 用数组发送多个邮件
        // String[] array = new String[] {"sun111@163.com","sun222@sohu.com"};
        // mailMessage.setTo(array);
        mailMessage.setTo("292634346@qq.com");
        mailMessage.setFrom("15198724503@163.com");
        mailMessage.setSubject("问候");
        mailMessage.setText("和风吹拂，三月阳春，江南草长，江北水暖，岭外梅香，塞上柳软，又是踏青寻芳季节，其实我想说的是……新年到了，春暖花开，你什么时候请我吃饭。");

        senderImpl.setUsername("15198724503@163.com"); // 根据自己的情况,设置username
        senderImpl.setPassword("ytt5201314"); // 根据自己的情况, 设置password
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", "25000");

        senderImpl.setJavaMailProperties(prop);
        // 发送邮件
        senderImpl.send(mailMessage);
        System.out.println(" 邮件发送成功.. ");


        /*JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        // 设定mail server
        senderImpl.setHost("smtp.qq.com");

        // 建立邮件消息
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人 用数组发送多个邮件
        // String[] array = new String[] {"sun111@163.com","sun222@sohu.com"};
        // mailMessage.setTo(array);
        mailMessage.setTo("292634346@qq.com");
        mailMessage.setFrom("1518436292@qq.com");
        mailMessage.setSubject("问候");
        mailMessage.setText("和风吹拂，三月阳春，江南草长，江北水暖，岭外梅香，塞上柳软，又是踏青寻芳季节，其实我想说的是……新年到了，春暖花开，你什么时候请我吃饭。");

        senderImpl.setUsername("1518436292@qq.com"); // 根据自己的情况,设置username
        senderImpl.setPassword("ruzmgycwrudafhab"); // 根据自己的情况, 设置password
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", "25000");
        senderImpl.setJavaMailProperties(prop);
        // 发送邮件
        senderImpl.send(mailMessage);
        System.out.println(" 邮件发送成功.. ");*/


    }
}

