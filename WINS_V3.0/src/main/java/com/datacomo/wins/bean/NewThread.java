package com.datacomo.wins.bean;

import com.datacomo.wins.util.ApiConfigUtil;
import com.datacomo.wins.util.ApiRequestUtil;
import org.apache.log4j.Logger;

/**
 * Created by yangxiongbin on 2016/9/28.
 */

public class NewThread implements Runnable {
    private static Logger logger = Logger.getLogger(NewThread.class.getName());
    private static String _lock = new String("_lock");
    private static int _result = 0;

    public  static int get_result(){
        synchronized (_lock){
            return _result;
        }
    }
    public static void set_result(int _result) {
        synchronized (_lock) {
            NewThread._result = _result;
        }
    }

    @Override
    public void run() {
        logger.info("startExcuteRequest method start");
        String startApi = String.valueOf(ApiConfigUtil.message.get("startExecuteApi"));
        String endApi = String.valueOf(ApiConfigUtil.message.get("endExecuteApi"));
        if (logger.isDebugEnabled()) {
            logger.debug("startApi " + startApi);
            logger.debug("endApi " + endApi);
        }
        long startTime = System.currentTimeMillis();
        long timeOut =0;
        long sleepTime =600000;  //设置10分钟睡眠时间 10*60*1000=600000ms
        while (true) {
            int result = -1;
            timeOut = System.currentTimeMillis() - startTime;
            if(timeOut>=82800000){  //不能超过23小时 23*60*60*1000=82800000ms
                result = 2 ;//超时
                NewThread.set_result(result);
                break;
            }
            try {
                result = ApiRequestUtil.executeRequest(startApi);
                if (result == 1) {
                    ApiRequestUtil.executeRequest(endApi);
                    break;
                }
                NewThread.set_result(result);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error(e);
            }
        }
        logger.info("startExcuteRequest method end");
    }

}
