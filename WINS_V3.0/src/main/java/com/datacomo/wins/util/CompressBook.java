/**
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * Copyright 2010-2014 DataComo Communications Technology INC.
 * 
 * This source file is a part of hirouter project. 
 * date: 2014年11月19日
 *
 */
package com.datacomo.wins.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.Logger;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarOutputStream;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 
 * @author zhujigao
 * @date 2014年11月19日 下午1:46:38
 * @update developer zhujigao
 * @update date 2014年11月19日 下午1:46:38
 * @version v1.0.0
 */

/**
 * 功能:zip压缩、解压(支持中文文件名) 说明:使用Apache Ant提供的zip工具org.apache.tools.zip实现zip压缩和解压功能.
 * 解决了由于java.util.zip包不支持汉字的问题。 使用java.util.zip包时,当zip文件中有名字为中文的文件时, 就会出现异常:
 * "Exception  in thread "main " java.lang.IllegalArgumentException at
 * java.util.zip.ZipInputStream.getUTF8String(ZipInputStream.java:285)
 * 
 * 
 * 注意: 1、使用时把ant.jar放到classpath中,程序中使用 import org.apache.tools.zip.*; 2、本程序使用Ant
 * 1.7.1 中的ant.jar部分类，ant-zip-1.7.1只保留Ant的zip压缩功能，以减小ant.jar的大小。
 * 4、ZipEntry的isDirectory()方法中,目录以"/"结尾。
 * 
 * 
 * ------------------------------------------------ 可将主函数注释去掉以单独测试ZipFileUtils类。
 * Compile: javac -cp Ant.jar ZipFileUtils.java
 * 
 * Usage:(将ant.jar直接放在当前目录) 压缩: java -cp Ant.jar;. ZipFileUtils -zip
 * [directoryName | fileName]... 解压: java -cp Ant.jar;. ZipFileUtils -unzip
 * "fileName.zip"
 * 
 * ------------------------------------------------
 */
public class CompressBook {
	private static Logger log = Logger.getLogger(CompressBook.class);

	private static int bufSize = 8096; // size of bytes

	/**
	 * 压缩文件夹内的所有文件和目录(不支持有中文目录或文件名)。
	 * 
	 * @param zipDirectory
	 *            需要压缩的文件夹名
	 * @return 成功返回null，否则返回失败信息
	 */
	public static String zip(String zipDirectory) {
		File zipDir = new File(zipDirectory);
		return zip(zipDirectory, zipDir.getPath(), false);
	}

	/**
	 * 压缩文件夹内的所有文件和目录(不支持有中文目录或文件名)。
	 * 
	 * @param zipDirectory
	 *            需要压缩的文件夹名
	 * @param zipFileName
	 *            压缩后的zip文件名，如果后缀不是".zip, .jar, .war"， 自动添加后缀".zip"。
	 * @param includeSelfDir
	 *            是否包含自身文件夹
	 * @return 成功返回null，否则返回失败信息
	 */
	public static String zip(String zipDirectory, String zipFileName,
			boolean includeSelfDir) {
		File zipDir = new File(zipDirectory);
		File[] willZipFileArr;
		if (includeSelfDir || zipDir.isFile()) {
			willZipFileArr = new File[] { zipDir };
		} else {
			willZipFileArr = zipDir.listFiles();
		}
		return zip(willZipFileArr, zipFileName);
	}

	/**
	 * 压缩多个文件或目录。可以指定多个单独的文件或目录。
	 * 
	 * @param files
	 *            要压缩的文件或目录组成的File数组。
	 * @param zipFileName
	 *            压缩后的zip文件名，如果后缀不是".zip, .jar, .war"，自动添加后缀".zip"。
	 * @return 成功返回null，否则返回失败信息
	 */
	public static String zip(File[] files, String zipFileName) {
		if (files == null) {
			return "待压缩的文件不存在。";
		}

		// 未指定压缩文件名，默认为"ZipFile"
		if (zipFileName == null || zipFileName.equals(""))
			zipFileName = "ZipFile";

		// 添加".zip"后缀
		if (!zipFileName.toLowerCase().endsWith(".zip")
				&& !zipFileName.toLowerCase().endsWith(".jar")
				&& !zipFileName.toLowerCase().endsWith(".war"))
			zipFileName += ".zip";

		JarOutputStream jarOutput = null;
		try {
			jarOutput = new JarOutputStream(new FileOutputStream(zipFileName),
					new Manifest());

			for (File file : files) {
				zipFiles(file, jarOutput, "");
			}
			log.info("压缩文件成功：" + zipFileName);

		} catch (Exception e) {
			log.error("异常", e);
		} finally {
			if (jarOutput != null) {
				try {
					jarOutput.close();
				} catch (IOException e) {
					log.error("jarOutput.close()异常", e);
				}
			}
		}
		return null;
	}

	/**
	 * @param file
	 *            压缩文件
	 * @param jos
	 *            JarOutputStream
	 * @param pathName
	 *            相对路径
	 * @throws Exception
	 *             异常
	 */
	private static void zipFiles(File file, JarOutputStream jos, String pathName)
			throws Exception {
		String fileName = pathName + file.getName();
		if (file.isDirectory()) {
			fileName = fileName + "/";
			jos.putNextEntry(new JarEntry(fileName));
			String fileNames[] = file.list();
			if (fileNames != null) {
				for (int i = 0; i < fileNames.length; i++) {
					zipFiles(new File(file, fileNames[i]), jos, fileName);
				}
				jos.closeEntry();
			}
		} else {
			JarEntry jarEntry = new JarEntry(fileName);
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(file));
			jos.putNextEntry(jarEntry);

			byte[] buf = new byte[bufSize];
			int len;
			while ((len = in.read(buf)) >= 0) {
				jos.write(buf, 0, len);
			}
			in.close();
			jos.closeEntry();
		}
	}

	/**
	 * 压缩文件夹内的所有文件和目录。
	 * 
	 * @param zipDirectory
	 *            需要压缩的文件夹名
	 * @return 成功返回null，否则返回失败信息
	 */
	public static String antzip(String zipDirectory) {
		File zipDir = new File(zipDirectory);
		return antzip(zipDirectory, zipDir.getPath(), false);
	}

	/**
	 * 压缩文件夹内的所有文件和目录。
	 * 
	 * @param zipDirectory
	 *            需要压缩的文件夹名
	 * @param zipFileName
	 *            压缩后的zip文件名，如果后缀不是".zip, .jar, .war"， 自动添加后缀".zip"。
	 * @param includeSelfDir
	 *            是否包含自身文件夹
	 * @return 成功返回null，否则返回失败信息
	 */
	public static String antzip(String zipDirectory, String zipFileName,
			boolean includeSelfDir) {
		File zipDir = new File(zipDirectory);
		File[] willZipFileArr;
		if (includeSelfDir || zipDir.isFile()) {
			willZipFileArr = new File[] { zipDir };
		} else {
			willZipFileArr = zipDir.listFiles();
		}
		return antzip(willZipFileArr, zipFileName);
	}

	/**
	 * 压缩多个文件或目录。可以指定多个单独的文件或目录。
	 * 
	 * @param files
	 *            要压缩的文件或目录组成的File数组。
	 * @param zipFileName
	 *            压缩后的zip文件名，如果后缀不是".zip, .jar, .war"，自动添加后缀".zip"。
	 * @return 成功返回null，否则返回失败信息
	 */
	public static String antzip(File[] files, String zipFileName) {
		// 未指定压缩文件名，默认为"ZipFile"
		if (zipFileName == null || zipFileName.equals(""))
			zipFileName = "ZipFile";

		// 添加".zip"后缀
		if (!zipFileName.toLowerCase().endsWith(".zip")
				&& !zipFileName.toLowerCase().endsWith(".jar")
				&& !zipFileName.toLowerCase().endsWith(".war"))
			zipFileName += ".zip";

		ZipOutputStream zipOutput = null;
		try {
			zipOutput = new ZipOutputStream(new BufferedOutputStream(
					new FileOutputStream(zipFileName)));
			zipOutput.setEncoding("GBK");

			for (File file : files) {
				antzipFiles(file, zipOutput, "");
			}

			log.info("压缩文件成功：" + zipFileName);
		} catch (Exception e) {
			log.error("压缩文件异常", e);
			return e.getMessage();
		} finally {
			try {
				assert zipOutput != null;
				zipOutput.close();
			} catch (Exception e) {
				log.error("异常", e);
			}
		}
		return null;
	}

	/**
	 * @param file
	 *            压缩文件
	 * @param zipOutput
	 *            ZipOutputStream
	 * @param pathName
	 *            相对路径
	 * @throws Exception
	 *             异常
	 */
	private static void antzipFiles(File file, ZipOutputStream zipOutput,
			String pathName) throws Exception {
		String fileName = pathName + file.getName();
		if (file.isDirectory()) {
			fileName = fileName + "/";
			zipOutput.putNextEntry(new ZipEntry(fileName));
			String fileNames[] = file.list();
			if (fileNames != null) {
				for (int i = 0; i < fileNames.length; i++) {
					antzipFiles(new File(file, fileNames[i]), zipOutput,
							fileName);
				}
				zipOutput.closeEntry();
			}
		} else {
			ZipEntry jarEntry = new ZipEntry(fileName);
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(file));
			zipOutput.putNextEntry(jarEntry);

			byte[] buf = new byte[bufSize];
			int len;
			while ((len = in.read(buf)) >= 0) {
				zipOutput.write(buf, 0, len);
			}
			in.close();
			zipOutput.closeEntry();
		}
	}

	/**
	 * 解压指定zip文件。
	 * 
	 * @param unZipFile
	 *            需要解压的zip文件对象
	 * @return 成功返回null，否则返回失败信息
	 */
	public static String unZip(File unZipFile) {
		return unZip(unZipFile.getPath(), null);
	}

	/**
	 * 解压指定zip文件到指定的目录。
	 * 
	 * @param unZipFile
	 *            需要解压的zip文件对象
	 * @param destFileName
	 *            解压目的目录
	 * @return 成功返回null，否则返回失败信息
	 */
	public static String unZip(File unZipFile, String destFileName) {
		return unZip(unZipFile.getPath(), destFileName);
	}

	/**
	 * 解压指定zip文件。
	 * 
	 * @param unZipFileName
	 *            需要解压的zip文件名
	 * @return 成功返回null，否则返回失败信息
	 */
	public static String unZip(String unZipFileName) {
		return unZip(unZipFileName, null);
	}

	/**
	 * 解压指定zip文件到指定的目录。
	 * 
	 * @param unZipFileName
	 *            需要解压的zip文件名
	 * @param destFileName
	 *            解压目的目录，如果为null则为当前zip文件所有目录
	 * @return 成功返回null，否则返回失败信息
	 */
	public static String unZip(String unZipFileName, String destFileName) {
		File unzipFile = new File(unZipFileName);

		if (destFileName == null || destFileName.trim().length() == 0) {
			destFileName = unzipFile.getParent();
		}

		File destFile;
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(unzipFile, "GBK");
			for (Enumeration entries = zipFile.getEntries(); entries
					.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				destFile = new File(destFileName, entry.getName());

				unZipFile(destFile, zipFile, entry); // 执行解压
			}
		} catch (Exception e) {
			log.error("解压ZIP文件异常", e);
			return e.getMessage();
		} finally {
			try {
				assert zipFile != null;
				zipFile.close();
			} catch (Exception e) {
				log.error("异常", e);
			}
		}
		return null;
	}

	/* 执行解压 */
	private static void unZipFile(File destFile, ZipFile zipFile, ZipEntry entry)
			throws IOException {
		InputStream inputStream;
		FileOutputStream fileOut;
		if (entry.isDirectory()) // 是目录，则创建之
		{
			destFile.mkdirs();
		} else // 是文件
		{
			// 如果指定文件的父目录不存在,则创建之.
			File parent = destFile.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}

			inputStream = zipFile.getInputStream(entry);

			fileOut = new FileOutputStream(destFile);
			byte[] buf = new byte[bufSize];
			int readedBytes;
			while ((readedBytes = inputStream.read(buf)) > 0) {
				fileOut.write(buf, 0, readedBytes);
			}
			fileOut.close();

			inputStream.close();
		}
	}

	/**
	 * 设置压缩或解压时缓冲区大小，单位字节。
	 * 
	 * @param bufSize
	 *            缓冲区大小
	 */
	public void setBufSize(int bufSize) {
		CompressBook.bufSize = bufSize;
	}

	/**
	 * 压缩文件成Gzip格式，Linux上可使用 压缩文件夹生成后缀名为".gz"的文件并下载
	 * 
	 * @param folderPath
	 *            ,要压缩的文件夹的路径
	 * @param zipFilePath
	 *            ,压缩后文件的路径
	 * @param zipFileName
	 *            ,压缩后文件的名称
	 * @throws BizException
	 * */
	public static void CompressedFiles_Gzip(String folderPath,
			String targzipFilePath, String targzipFileName) {
		File srcPath = new File(folderPath);
		int length = srcPath.listFiles().length;
		byte[] buf = new byte[1024]; // 设定读入缓冲区尺寸
		File[] files = srcPath.listFiles();
		try {
			// 建立压缩文件输出流
			FileOutputStream fout = new FileOutputStream(targzipFilePath);
			// 建立tar压缩输出流
			TarOutputStream tout = new TarOutputStream(fout);
			for (int i = 0; i < length; i++) {
				String filename = srcPath.getPath() + File.separator
						+ files[i].getName();
				// 打开需压缩文件作为文件输入流
				FileInputStream fin = new FileInputStream(filename); // filename是文件全路径
				TarEntry tarEn = new TarEntry(files[i]); // 此处必须使用new
															// TarEntry(File
															// file);
				tarEn.setName(files[i].getName()); // 此处需重置名称，默认是带全路径的，否则打包后会带全路径
				tout.putNextEntry(tarEn);
				int num;
				while ((num = fin.read(buf)) != -1) {
					tout.write(buf, 0, num);
				}
				tout.closeEntry();
				fin.close();
			}
			tout.close();
			fout.close();

			// 建立压缩文件输出流
			FileOutputStream gzFile = new FileOutputStream(targzipFilePath
					+ ".gz");
			// 建立gzip压缩输出流
			GZIPOutputStream gzout = new GZIPOutputStream(gzFile);
			// 打开需压缩文件作为文件输入流
			FileInputStream tarin = new FileInputStream(targzipFilePath); // targzipFilePath是文件全路径
			int len;
			while ((len = tarin.read(buf)) != -1) {
				gzout.write(buf, 0, len);
			}
			gzout.close();
			gzFile.close();
			tarin.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	// 主函数，用于测试ZipFileUtils类
	public static void main(String[] args) throws Exception {
		// ZipFileUtils zip = new ZipFileUtils();
		// if(args.length >= 2)
		// {
		//
		// if(args[0].equals("-zip"))
		// {
		// //将后续参数全部转化为File对象
		// File[] files = new File[args.length - 1];
		// for(int i = 0; i < args.length - 1; i++)
		// {
		// files[i] = new File(args[i + 1]);
		// }
		//
		// //将第一个文件名作为zip文件名
		// CompressBook.antzip(files, files[0].getName());
		// return;
		// }
		// else if(args[0].equals("-unzip"))
		// {
		// CompressBook.unZip(args[1]);
		// return;
		// }
		// }
		//
		// String tmpFile =
		// "D:/MyDocuments/Doc2009/2009-04/test/tagTable中文测试.zip";
		// CompressBook.unZip(tmpFile);
		// CompressBook.unZip(tmpFile, tmpFile.substring(0,
		// tmpFile.lastIndexOf(".")));
		//
		// String tmpDir =
		// "D:\\SVN\\USCE.USEE\\PCT\\trunk\\src\\PCT_CJ001_Application\\src\\main\\webapp\\portals\\king0.1\\webapp";
		// //ZipFileUtils.antzip(tmpDir, tmpDir + "/../war/pctant.war", false);
		// CompressBook.zip(tmpDir + "/test");
		// //ZipFileUtils.zip(tmpDir, tmpDir + "/../war/pct.war", false);
		//
		// // -----------------
		// System.out.println("Usage:");
		// System.out.println("压缩:java ZipFileUtils -zip [directoryName | fileName]... ");
		// System.out.println("解压:java ZipFileUtils -unzip fileName.zip");
		// System.out.println();
		// antzip("D:\\apache-tomcat-6.0.30\\webapps\\hr\\fileTemplate\\tempFiles","c:\\yasuo.rar",false);
		String str = "/Users/zhujigao/Desktop/git/hi_router/hirouter/WebRoot/templates/temp_preview/1416365865696";
		zip(str);
		// File[] sources = new File[] { new
		// File("/Users/zhujigao/Desktop/git/hi_router/hirouter/WebRoot/templates/temp_preview/1416365865696.zip")};
		// File target = new
		// File("/Users/zhujigao/Desktop/git/hi_router/hirouter/WebRoot/templates/temp_preview/1416365865696.tar");
		// GZIPUtil.compress(target);

//		GZIPUtil.compress(new File(str + ".zip"));
//		CompressedFiles_Gzip("/Users/zhujigao/Desktop/git/hi_router/hirouter/WebRoot/templates/temp_preview/1416365865696.zip","/Users/zhujigao/Desktop/git/hi_router/hirouter/WebRoot/templates/temp_preview/1416365865696.tar");

		CompressedFiles_Gzip(str + ".zip",str,"");
		
	}
}
