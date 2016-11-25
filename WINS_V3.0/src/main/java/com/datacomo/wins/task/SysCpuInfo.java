package com.datacomo.wins.task;

import java.io.*;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 
 * @author Duanlinzhuo
 *
 */
public class SysCpuInfo {

	private int CPU_IDLE = 0;
	int processStatus = 0;

	public SysCpuInfo(Session session) {
		InputStream is = null;
		BufferedReader brStat = null;
		Session sess = null;
		String str = "";
		/**
		 * 对于执行linux shell.
		 */
		try {
			sess = session;
			sess.execCommand("vmstat 1 3 |sed -n '3,$p' |awk '{x = x + $15} END {print x/3}' |awk -F. '{print $1}'");
			is = new StreamGobbler(sess.getStdout());

			brStat = new BufferedReader(new InputStreamReader(is));
			/*
			 * 先读取第一行Title信息 procs -----------memory---------- ---swap--
			 * -----io---- --system-- -----cpu------
			 */
			str = brStat.readLine();
			CPU_IDLE = 100 - new Integer(str).intValue();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public int getCPUInfo() {
		return CPU_IDLE;
	}

}
