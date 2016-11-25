package com.datacomo.wins.task;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class SysDiskInfo {
	
	private int DISK_IDLE = 0;
	int processStatus = 0;

	public SysDiskInfo(Connection connection,Session session,String name) {
		InputStream is = null;
		BufferedReader brStat = null;
		Session sess = null;
		Connection conn = null;
		String str = "";
		String diskName = name;
		/**
		 * 对于执行linux shell.
		 */
		try {
			sess = session;
			sess.execCommand("df -h "+diskName+"|grep "+diskName+"|awk '{print $5}'|grep -o -P '\\d+'");
			is = new StreamGobbler(sess.getStdout());

			brStat = new BufferedReader(new InputStreamReader(is));
			/*
			 * 先读取第一行Title信息 procs -----------memory---------- ---swap--
			 * -----io---- --system-- -----cpu------
			 */
			str = brStat.readLine();
			if(str==""||str==null) {
				sess.close();
				conn = connection;
				sess = conn.openSession();
				sess.execCommand("df -h "+diskName+"|grep -v "+diskName+"|grep -v Filesystem|awk '{print $4}'|grep -o -P '\\d+'");
				is = new StreamGobbler(sess.getStdout());

				brStat = new BufferedReader(new InputStreamReader(is));
				/*
				 * 先读取第一行Title信息 procs -----------memory---------- ---swap--
				 * -----io---- --system-- -----cpu------
				 */
				str = brStat.readLine();
			}
			DISK_IDLE = new Integer(str).intValue();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public int getDISKInfo() {
		return DISK_IDLE;
	}
}
