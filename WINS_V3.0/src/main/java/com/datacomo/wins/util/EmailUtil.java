package com.datacomo.wins.util;

import com.datacomo.wins.base.sendMail.SendMailBySSL;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.util.*;

/**
 * Created by yangxiongbin on 2016/9/26.
 */
public class EmailUtil {
    public static Map<String,Object> message = new HashMap<String,Object>();
    public static ObjectMapper objectMapper = null;
    static {
        String path = ApiConfigUtil.class.getResource("/").getPath() + "sendEmail.properties";
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(path);
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
            properties.load(bf);
            inputStream.close();
            for (Object key : properties.keySet()) {
                message.put(String.valueOf(StringUtils.trim(String.valueOf(key))), StringUtils.trim(String.valueOf(properties.get(key))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送html邮件
     * @param to 收件人
     * @param subject 邮件主题
     * @param concent 邮件内容
     * @param attachmentName 附件路径
     * @return
     */
    public static boolean sendHtmlEmail(String to,String subject,String concent,String attachmentName){
        boolean result = false;
        String smtpServer = String.valueOf(EmailUtil.message.get("smtpServer"));
        String port = String.valueOf(EmailUtil.message.get("port"));
        String username = String.valueOf(EmailUtil.message.get("username"));
        String password = String.valueOf(EmailUtil.message.get("password"));
        List<String> recipients = new ArrayList<String>();
        recipients.add(to);
        List<String> attachmentNames = new ArrayList<String>();
        if(!attachmentName.equals("")){ //是否有附件
            attachmentNames.add(attachmentName);
        }
        SendMailBySSL sendMailBySSL = new SendMailBySSL(smtpServer, port,username, password, recipients, subject, concent,attachmentNames);
        result = sendMailBySSL.sendHtmlMail();
        return result;
    }

    /**
     * 发送text邮件
     * @param to 收件人
     * @param subject 邮件主题
     * @param concent 邮件内容
     * @param attachmentName 附件路径
     * @return
     */
    public static boolean sendTextEmail(String to,String subject,String concent,String attachmentName){
        boolean result = false;
        String smtpServer = String.valueOf(EmailUtil.message.get("smtpServer"));
        String port = String.valueOf(EmailUtil.message.get("port"));
        String username = String.valueOf(EmailUtil.message.get("username"));
        String password = String.valueOf(EmailUtil.message.get("password"));
        List<String> recipients = new ArrayList<String>();
        recipients.add(to);
        List<String> attachmentNames = new ArrayList<String>();
        if(!attachmentName.equals("")){ //是否有附件
            attachmentNames.add(attachmentName);
        }
        SendMailBySSL sendMailBySSL = new SendMailBySSL(smtpServer, port,username, password, recipients, subject, concent,attachmentNames);
        result = sendMailBySSL.sendTextMail();
        return result;
    }

    //测试
    public static void main(String args[]){
        EmailUtil.sendHtmlEmail("292634346@qq.com","来自datacomo的邮箱验证","<h2 style='color:red;'>你本次创建账户的验证码是063205</h2>","");
    }
}
