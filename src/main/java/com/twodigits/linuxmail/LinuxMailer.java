package com.twodigits.linuxmail;

/**
 * provides mail sending functionality on Linux environments
 * @author robert.diers
 *
 */
public class LinuxMailer {
	
	private static String contentfile_template = "_LinuxMailer_content.txt";
	private static String shellfile_template = "_LinuxMailer_send.sh";
	
	/**
	 * sendet eine simple Testmail, URLs werden von Outlook interpretiert
	 * @param from a@b.de
	 * @param to c@d.de,e@f.de
	 * @param subject Hallo
	 * @param content Text mit \n fuer neue Zeile
	 * @throws LinuxMailException 
	 */
	public static void sendTextMail(String from, String to, String subject, String content) throws LinuxMailException {
		FileHandler fh = new FileHandler();
		//create unique file names
		long millis = System.currentTimeMillis();
		String contentfile = millis + contentfile_template;
		String shellfile = millis + shellfile_template;
		try {
			//create file with content			
			fh.createFile(contentfile, content);
			//create shell script
			StringBuffer buf = new StringBuffer();
			buf.append("#!/bin/bash");
			buf.append("\n");
			buf.append("mailx -s \""+subject+"\" -r "+from+" "+to+" <"+contentfile);
			buf.append("\n");
			fh.createFile(shellfile, buf.toString());
			//execute shell script
			fh.executeFile(shellfile);
		} catch (Exception e) {
			e.printStackTrace();			
			throw new LinuxMailException(e.getMessage());
		} finally {
			//try to delete
			try{
				fh.deleteFile(contentfile);
				fh.deleteFile(shellfile);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * simuliert eine simple Testmail, bitte pruefen Sie das aktuelle Verzeichnis auf Dateien
	 * @param from a@b.de
	 * @param to c@d.de,e@f.de
	 * @param subject Hallo
	 * @param content Text mit \n fuer neue Zeile
	 * @throws LinuxMailException 
	 */
	public static void simulateSendTextMail(String from, String to, String subject, String content) throws LinuxMailException {
		FileHandler fh = new FileHandler();
		//create unique file names
		long millis = System.currentTimeMillis();
		String contentfile = millis + contentfile_template;
		String shellfile = millis + shellfile_template;
		try {
			//create file with content			
			fh.createFile(contentfile, content);
			//create shell script
			StringBuffer buf = new StringBuffer();
			buf.append("#!/bin/bash");
			buf.append("\n");
			buf.append("mailx -s \""+subject+"\" -r "+from+" "+to+" <"+contentfile);
			buf.append("\n");
			fh.createFile(shellfile, buf.toString()); //no executable
			//no execution
		} catch (Exception e) {
			e.printStackTrace();			
			throw new LinuxMailException(e.getMessage());
		} 
		//no delete
	}
	
	/**
	 * sendet eine HTML Mail
	 * @param from a@b.de
	 * @param to c@d.de,e@f.de
	 * @param subject Hallo
	 * @param content HTML Code
	 * @throws LinuxMailException 
	 */
	public static void sendHtmlMail(String from, String to, String subject, String htmlcontent) throws LinuxMailException {
		FileHandler fh = new FileHandler();
		//create unique file names
		long millis = System.currentTimeMillis();
		String contentfile = millis + contentfile_template;
		String shellfile = millis + shellfile_template;
		try {
			//create file with content
			/*
			To: robert.diers@accenture.com
			From: robert.diers@partner.bmw.de
			Subject: testsubject#2
			Content-Type: text/html; charset="UTF-8"
			*/
			StringBuffer content = new StringBuffer();
			content.append("To: "+to+" \n");
			content.append("From: "+from+" \n");
			content.append("Subject: "+subject+" \n");
			content.append("Content-Type: text/html; charset=\"UTF-8\" \n");
			content.append(htmlcontent);
			fh.createFile(contentfile, content.toString());
			//create shell script
			StringBuffer buf = new StringBuffer();
			buf.append("#!/bin/bash");
			buf.append("\n");
			buf.append("/usr/sbin/sendmail "+to+" <"+contentfile);
			buf.append("\n");
			fh.createFile(shellfile, buf.toString());
			//execute shell script
			fh.executeFile(shellfile);
		} catch (Exception e) {
			e.printStackTrace();			
			throw new LinuxMailException(e.getMessage());
		} finally {
			//try to delete
			try{
				fh.deleteFile(contentfile);
				fh.deleteFile(shellfile);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * simuliert eine HTML Mail
	 * @param from a@b.de
	 * @param to c@d.de,e@f.de
	 * @param subject Hallo
	 * @param content HTML Code
	 * @throws LinuxMailException 
	 */
	public static void simulateSendHtmlMail(String from, String to, String subject, String htmlcontent) throws LinuxMailException {
		FileHandler fh = new FileHandler();
		//create unique file names
		long millis = System.currentTimeMillis();
		String contentfile = millis + contentfile_template;
		String shellfile = millis + shellfile_template;
		try {
			//create file with content
			/*
			To: robert.diers@accenture.com
			From: robert.diers@partner.bmw.de
			Subject: testsubject#2
			Content-Type: text/html; charset="UTF-8"
			*/
			StringBuffer content = new StringBuffer();
			content.append("To: "+to+" \n");
			content.append("From: "+from+" \n");
			content.append("Subject: "+subject+" \n");
			content.append("Content-Type: text/html; charset=\"UTF-8\" \n");
			content.append(htmlcontent);
			fh.createFile(contentfile, content.toString());
			//create shell script
			StringBuffer buf = new StringBuffer();
			buf.append("#!/bin/bash");
			buf.append("\n");
			buf.append("/usr/sbin/sendmail "+to+" <"+contentfile);
			buf.append("\n");
			fh.createFile(shellfile, buf.toString());
			//no execution
		} catch (Exception e) {
			e.printStackTrace();			
			throw new LinuxMailException(e.getMessage());
		} 
		//no delete
	}

}
