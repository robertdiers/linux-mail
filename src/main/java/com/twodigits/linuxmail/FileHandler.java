package com.twodigits.linuxmail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * file handling activities
 * @author robert.diers
 */
public class FileHandler {	

	/**
	 * create file on Linux
	 * @param filename
	 * @param content
	 * @throws IOException
	 */
	public void createFile(String filename, String content) throws IOException {
		//cleanup		
		File file = new File(filename);
		if (file.exists()) {
			file.delete();
		}
		//create file		
		BufferedWriter output = new BufferedWriter(new FileWriter(file));
		try {
			output.write(content);
			output.flush();			
		} finally {
			if (output != null) output.close();
		}
		//wait till file is written
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {}		
	}	
	
	/**
	 * execute file with Shell
	 * @param filename
	 * @throws IOException
	 */
	public void executeFile(String filename) throws IOException {
		//make executable
		Runtime.getRuntime().exec("chmod 777 " + filename);
	    try {
			Thread.sleep(100);
		} catch (InterruptedException e) {}
	    //execute
		//Runtime.getRuntime().exec("./" + filename + " >> "+filename+".log");
		Runtime.getRuntime().exec("./" + filename);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {}
	}
	
	/**
	 * delete file on Linux
	 * @param filename
	 */
	public void deleteFile(String filename) {		
		File file = new File(filename);
		if (file.exists()) {
			file.delete();
		}
	}
	
	/**
	 * read File from Linux
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public String readFile(String filename) throws IOException
	{
	   String content = null;
	   File file = new File(filename);
	   FileReader reader = null;
	   try {
	       reader = new FileReader(file);
	       char[] chars = new char[(int) file.length()];
	       reader.read(chars);
	       content = new String(chars);
	       reader.close();
	   } finally {
		   if (reader != null) reader.close();
	   }
	   return content;
	}

}
