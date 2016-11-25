package com.datacomo.wins.service;

/**
 * Created by yangxiongbin on 2016/9/26.
 */
public interface SendEmailService  {
    /**
     * 发送策略审核成功的邮件
     * @param userId
     * @param Ip
     * @param policyName
     * @return
     */
    public boolean sendPolicyAuditeSuccessEmail(int userId,String Ip,String policyName);

    /**
     * 发送策略待审核的邮件
     * @param userId
     * @param Ip
     * @param policyName
     * @return
     */
    public boolean sendPolicyWaitToAuditeEmail(int userId,String Ip,String policyName);

    /**
     * 发送策略开始/停止运行的邮件
     * @param userId
     * @param Ip
     * @param policyName
     * @param bool ---->true:开始运行 false:停止运行
     * @return
     */
    public boolean sendPolicyStartOrStopEmail(int userId,String Ip,String policyName,boolean bool);
}
