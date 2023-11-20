package com.project.two.utills;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
	private String fileName = "";
	private File logFile = null;
	private PrintWriter logWriter = null;
	private boolean isLogs = false;
	
	public Logger () throws IOException {
		this.initialize(null);
	}
	
	public Logger (String fileName) throws IOException {
		this.initialize(fileName);
	}
	
	private void initialize (String fileName) throws IOException {
		String logDir = "./logs";
		File logFDir = new File (logDir);
		if (!logFDir.exists()) {
			logFDir.mkdirs();
		}
		this.fileName = (fileName == null || fileName == "") ? "Server_log_" + System.currentTimeMillis() + ".txt" : fileName;
		this.fileName = logDir + "/" + this.fileName;
		this.logFile = new File(this.fileName);
		if (this.logFile.exists()) {
			this.logFile.delete();
		}
		this.logFile.createNewFile();
		this.logWriter = new PrintWriter(this.logFile);
	}
	
	public void close () {
		this.logWriter.close();
	}
	
	public void loggerEx (Exception e) {
		e.printStackTrace(this.logWriter);
	}
	
	public void logger (String msg) {
		try {
			this.logWriter.println(msg);
			this.logWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (this.isLogs) {
			System.out.println(msg + " - " + System.currentTimeMillis());
		}
	}

	public boolean isLogs() {
		return isLogs;
	}

	public void setLogs(boolean isLogs) {
		this.isLogs = isLogs;
	}
	
	
}
