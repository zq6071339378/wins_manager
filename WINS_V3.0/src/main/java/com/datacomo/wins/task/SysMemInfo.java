package com.datacomo.wins.task;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class SysMemInfo {
	private int MEM_IDLE = 0;
	int processStatus = 0;

	public SysMemInfo(Session session) {
		InputStream is = null;
		BufferedReader brStat = null;
		Session sess = null;
		String str = "";
		/**
		 * 对于执行linux shell.
		 */
		try {
			sess = session;
			sess.execCommand("free|grep Mem:|awk '{print int(($4 + $6 + $7) / $2 * 100)}'");
			is = new StreamGobbler(sess.getStdout());

			brStat = new BufferedReader(new InputStreamReader(is));
			/*
			 * 先读取第一行Title信息 procs -----------memory---------- ---swap--
			 * -----io---- --system-- -----cpu------
			 */
			str = brStat.readLine();
			MEM_IDLE = 100 - new Integer(str).intValue();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public int getMEMInfo() {
		return MEM_IDLE;
	}
}
