package com.datacomo.wins.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

import com.datacomo.wins.exception.ServiceException;
import com.datacomo.wins.service.SysNewsInfoService;
import com.datacomo.wins.service.SysTaskService;


@Component("changeStateTask")
public class MonitorTask {
	private static Logger logger = Logger
			.getLogger(MonitorTask.class.getName());
	@Autowired
	SysTaskService sysTaskService;
	@Autowired
	SysNewsInfoService sysNewsInfoService;
//	@Value("${monitor.username}")
//	private String username;
//	@Value("${monitor.password}")
//	private String password;
//	@Value("${monitor.diskname}")
//	private String diskname;

	@Scheduled(cron = "0 0 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23 * * ?")
	public void doJob() {
		logger.info("monitorTask method start");
//		Properties pro = new Properties();
//		try {
//			InputStream inStr = Thread.currentThread().getContextClassLoader().getResourceAsStream("serverconfig.properties");
//			pro.load(inStr);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		Iterator it = pro.entrySet().iterator();
		List<Map<String, Object>> serverResult = null;
		try {
			serverResult = sysTaskService.getServerInfo();
		} catch (ServiceException e1) {
			e1.printStackTrace();
			logger.error(e1);
		}
		for (Map<String, Object> m : serverResult) {
			String servername = (String) m.get("Server_Name");
			String ip = (String) m.get("Server_Ip");
			String port = (String) m.get("Server_Port");
			String username = (String) m.get("User_Name");
			String password = (String) m.get("User_Passwd");
			String diskname = (String) m.get("Master_Name");
			Connection conn = null;
			boolean isAuthenticated = false;
			logger.info(servername + "Task method start");
			try {
				/* Create a connection instance */

				conn = new Connection(ip, Integer.parseInt(port)); // hostname
				// 你要远程登录的主机IP地址,如10.0.2.1

				/* Now connect */

				conn.connect();

				/*
				 * Authenticate. If you get an IOException saying something like
				 * "Authentication method password not supported by the server at this stage."
				 * then please check the FAQ.
				 */

				isAuthenticated = conn.authenticateWithPassword(username,
						password); // 你要远程登录的主机的用户名及密码,如admin/123456
				// System.out.println("authenticate sucess ...........................");
				if (isAuthenticated == false)
					logger.info("SSH Login  Authentication failed.");
				else {
					/* Create a session */
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String Report_Date = sdf.format((new Date()).getTime());
					@SuppressWarnings("deprecation")
					int Report_Period = new Date().getHours();
					Map<String, Object> condition = new HashMap<String, Object>();
					condition.put("Report_Date", Report_Date);
					condition.put("Report_Period", Report_Period);
					condition.put("Server_Name", servername);
					condition.put("Server_Ip", ip);
					Session sess = conn.openSession();
					condition.put("Cpu_Usage",
							new SysCpuInfo(sess).getCPUInfo());
					/* 注意,一个session只能执行一次shell,因此,如果你要再执行shell的话,必须重新创建一个session */
					sess.close();
					sess = conn.openSession();
					condition.put("Memory_Usage",
							new SysMemInfo(sess).getMEMInfo());
					sess.close();
					sess = conn.openSession();
					condition.put("Harddisk_Usage", new SysDiskInfo(conn,sess,
							diskname).getDISKInfo());
					sess.close();
					sess = conn.openSession();
					condition.put("Network_Uplink",
							new SysUploadInfo(sess).getUPLOADInfo());
					sess.close();
					sess = conn.openSession();
					condition.put("Network_Downlink",
							new SysDownloadInfo(sess).getDOWNLOADInfo());
					sess.close();
					try {
						int result = sysTaskService.insertInfo(condition);
					} catch (ServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
			conn.close();
			logger.info(servername + "Task method end");
		}
		logger.info("monitorTask method end");
	}

	/**
	 * 固定每10分钟执行一次 ChangeStateTask.doJob1()<BR>
	 * <P>
	 * Author : Duanlinzhuo
	 * </P>
	 * <P>
	 * Date : 2016年6月1日
	 * </P>
	 */
	@Scheduled(fixedRate = 600 * 1000)
	public void doJob1() {
		logger.info("alarmTask method start");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = sysTaskService.getMonitorAlarmInfo();
		} catch (ServiceException e) {
			e.printStackTrace();
			logger.error(e);
		}
		Float Cpuf = (Float) result.get("Cpu_Limit");
		int Cpu_Limit = Cpuf.intValue();
		Float Memoryf = (Float) result.get("Memory_Limit");
		int Memory_Limit = Memoryf.intValue();
		Float Harddiskf = (Float) result.get("Harddisk_Limit");
		int Harddisk_Limit = Harddiskf.intValue();
		String Receive_Name = (String) result.get("Receive_Name");
		int User_Id = (int) result.get("User_Id");
		List<Map<String, Object>> serverResult = null;
		try {
			serverResult = sysTaskService.getServerInfo();
		} catch (ServiceException e1) {
			e1.printStackTrace();
			logger.error(e1);
		}
		for (Map<String, Object> m : serverResult) {
			String servername = (String) m.get("Server_Name");
			String ip = (String) m.get("Server_Ip");
			String port = (String) m.get("Server_Port");
			String username = (String) m.get("User_Name");
			String password = (String) m.get("User_Passwd");
			String diskname = (String) m.get("Master_Name");
			Connection conn = null;
			boolean isAuthenticated = false;
			logger.info(servername + "Task method start");
			try {
				/* Create a connection instance */

				conn = new Connection(ip, Integer.parseInt(port)); // hostname
				// 你要远程登录的主机IP地址,如10.0.2.1

				/* Now connect */

				conn.connect();

				/*
				 * Authenticate. If you get an IOException saying something like
				 * "Authentication method password not supported by the server at this stage."
				 * then please check the FAQ.
				 */

				isAuthenticated = conn.authenticateWithPassword(username,
						password); // 你要远程登录的主机的用户名及密码,如admin/123456
				// System.out.println("authenticate sucess ...........................");
				if (isAuthenticated == false)
					logger.info("SSH Login  Authentication failed.");
				else {
					Map<String, Object> condition = new HashMap<String, Object>();
					Session sess = conn.openSession();
					int Cpu_Usage = new SysCpuInfo(sess).getCPUInfo();
					if (Cpu_Usage >= Cpu_Limit) {
						condition.put("User_Id", User_Id);
						condition.put("News_Title", "监控告警");
						condition.put("News_Content", servername + "服务器CPU使用率超过"
								+ Cpu_Limit + "%");
						condition.put("News_Type", 4);
						condition.put("Create_User", 1);
						condition.put("Receive_Name", Receive_Name);
						condition.put("Is_Read", 1);
						sysNewsInfoService.sendMassages(0, condition);
					}
					/* 注意,一个session只能执行一次shell,因此,如果你要再执行shell的话,必须重新创建一个session */
					sess.close();
					sess = conn.openSession();
					int Mem_Usage = new SysMemInfo(sess).getMEMInfo();
					if (Mem_Usage >= Memory_Limit) {
						condition.put("User_Id", User_Id);
						condition.put("News_Title", "监控告警");
						condition.put("News_Content", servername + "服务器内存使用率超过"
								+ Memory_Limit + "%");
						condition.put("News_Type", 4);
						condition.put("Create_User", 1);
						condition.put("Receive_Name", Receive_Name);
						condition.put("Is_Read", 1);
						sysNewsInfoService.sendMassages(0, condition);
					}
					sess.close();
					sess = conn.openSession();
					int Disk_Usage = new SysDiskInfo(conn,sess, diskname)
							.getDISKInfo();
					if (Disk_Usage >= Harddisk_Limit) {
						condition.put("User_Id", User_Id);
						condition.put("News_Title", "监控告警");
						condition.put("News_Content", servername + "服务器硬盘占用率超过"
								+ Harddisk_Limit + "%");
						condition.put("News_Type", 4);
						condition.put("Create_User", 1);
						condition.put("Receive_Name", Receive_Name);
						condition.put("Is_Read", 1);
						sysNewsInfoService.sendMassages(0, condition);
					}
					sess.close();
					sess = conn.openSession();
					int Network_Uplink = new SysUploadInfo(sess)
							.getUPLOADInfo();
					sess.close();
					sess = conn.openSession();
					int Network_Downlink = new SysDownloadInfo(sess)
							.getDOWNLOADInfo();
					sess.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
			conn.close();
			logger.info(servername + "Task method end");
		}
		logger.info("alarmTask method end");
	}
	// /**
	// * 上次任务结束后一分钟后再次执行
	// * ChangeStateTask.doJob2()<BR>
	// * <P>Author : DingYinLong </P>
	// * <P>Date : 2014年8月1日 </P>
	// */
	// @Scheduled(fixedDelay = 60*1000)
	// public void doJob2(){
	// System.out.println(new Date() + "-----------------doJob2");
	// }
}
