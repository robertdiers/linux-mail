package com.twodigits.linuxmail;

public class MailSender {

	/**
	 * send Linux mail with main class
	 * usage: java -jar linuxmail.jar from to subject contentfile
	 * @param args
	 * @author robert.diers
	 */
	public static void main(String[] args) {
		
		try {
			if (args.length != 4) {
				System.out.println("java -jar linux-mail.jar from to subject contentfile");
				return;
			}
			
			String from = args[0];
			String to = args[1];
			String subject = args[2];
			String contentfile = args[3];
			FileHandler fh = new FileHandler();
			String content = fh.readFile(contentfile);
			
			//System.out.println(content);
			
			if (content.toUpperCase().contains("<HTML>")) {
				//HTML Mail
				//LinuxMailer.simulateSendHtmlMail(from, to, subject, content);
				LinuxMailer.sendHtmlMail(from, to, subject, content);
				System.out.println("HTML mail '"+subject+"' send from "+from+" to "+to);
			} else {
				//Text Mail
				LinuxMailer.sendTextMail(from, to, subject, content);
				System.out.println("Text mail '"+subject+"' send from "+from+" to "+to);
			}
			
		} catch (Exception e) {
			System.out.println("exception: "+e.getMessage());
			e.printStackTrace();
		}
		
	}	
	
}
