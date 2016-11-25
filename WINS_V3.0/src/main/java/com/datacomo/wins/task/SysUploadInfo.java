package com.datacomo.wins.task;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class SysUploadInfo {
	
	private int UPLOAD_IDLE = 0;
	int processStatus = 0;

	public SysUploadInfo(Session session) {
		InputStream is = null;
		BufferedReader brStat = null;
		Session sess = null;
		String strUp = "";
		/**
		 * 对于执行linux shell.
		 */
		try {
			sess = session;
			String encodeSet = "export LC_CTYPE=zh_CN.UTF-8;";
			sess.execCommand(encodeSet+"sar -n DEV -u 1 3|grep Average|sed -n '4,$p'|awk '{x = x + $5} END {print x}'|awk -F. '{print $1}'");
			is = new StreamGobbler(sess.getStdout());

			brStat = new BufferedReader(new InputStreamReader(is));
			/*
			 * 先读取第一行Title信息 procs -----------memory---------- ---swap--
			 * -----io---- --system-- -----cpu------
			 */
			strUp = brStat.readLine();
			UPLOAD_IDLE = new Integer(strUp).intValue();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public int getUPLOADInfo() {
		return UPLOAD_IDLE;
	}
}
