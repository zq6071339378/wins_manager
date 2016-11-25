package com.datacomo.wins.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @author liwenjie
 *
 */
public class zipNewName {
	
/**
 * 
 * @param files 拷贝文件
 * @param copy_path 目标目录
 * @throws IOException
 */
public static void copy(File[] files, File copy_path)throws IOException{
		
	
	if(!copy_path.exists()){
		copy_path.mkdirs();
	}
		for (int i = 0; i < files.length; i++) { //循环遍历要复制的文件夹
			if (files[i].isFile()) { //如果文件夹中是文件

			FileInputStream fis = new FileInputStream(files[i]); //创建FileInputStream对象
			FileOutputStream fos = new FileOutputStream(new File(copy_path.getPath()+ File.separator + files[i].getName())); //复制后文件的保存路径
			int len = 0;
			byte[] buf = new byte[1024*5];
			while ((len = fis.read(buf)) != -1) {
			    fos.write(buf, 0, len);
			} 
			fos.flush();
			fos.close(); //关闭流
			fis.close();
			}
			if (files[i].isDirectory()) { //如果文件夹中是一个路径
			File des = new File(copy_path.getPath()+ File.separator + files[i].getName()); //在复制后路径中创建子文件夹
			des.mkdir();
			copy(files[i].listFiles(), des); //再次调用本方法
			} 
			}
	}
/**
 * 
 * @param delpath 删除文件绝对路径
 * @return
 * @throws Exception
 */
public static boolean deletefile(String delpath) throws Exception {  
	  try {  
	  
	   File file = new File(delpath);  
	   // 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true  
	   if (!file.isDirectory()) {  
	    file.delete();  
	   } else if (file.isDirectory()) {  
	    String[] filelist = file.list();  
	    for (int i = 0; i < filelist.length; i++) {  
	     File delfile = new File(delpath + "\\" + filelist[i]);  
	     if (!delfile.isDirectory()) {  
	      delfile.delete();  
	      System.out  
	        .println(delfile.getAbsolutePath() + "删除文件成功");  
	     } else if (delfile.isDirectory()) {  
	      deletefile(delpath + "\\" + filelist[i]);  
	     }  
	    }  
	    System.out.println(file.getAbsolutePath()+"删除成功");  
	    file.delete();  
	   }  
	  
	  } catch (FileNotFoundException e) {  
	   System.out.println("deletefile() Exception:" + e.getMessage());  
	  }  
	  return true;  
	 } 

}
