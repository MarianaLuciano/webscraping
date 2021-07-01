package br.com.mariana.webscrapinggithub;

public class WebScrapingFiles {

	private String fileName;
		
	private int lines;
	
	private double size;
	
	private boolean isDirectory;
	
	private String pathDirectory;
		
	public WebScrapingFiles (String fileName, boolean isDirectory, String pathDirectory) {
		this.fileName = fileName;
		this.isDirectory = isDirectory;
		this.pathDirectory = pathDirectory;
	}

	public WebScrapingFiles(int countLine, double countSize) {
		this.lines = countLine;
		this.size = countSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getLines() {
		return lines;
	}
	
	public String getExtension() {
		if (fileName != null) {
			int dot = fileName.lastIndexOf(".");
			
			if (dot > 0) {
				return fileName.substring(dot);
			} else {
				return fileName.substring(fileName.lastIndexOf("/") + 1);
			}
		}
		
		return "";
	}

	public double getSize() {
		return size;
	}

	public void setSize(String size) {
		if (size.length() == 1) {
			this.size = 0;
			return;
		}
		
		int format = 1;
		
		if (size.contains("KB")) {
			format = 1024;
			
		} else if (size.contains("MB")) {
			
			format = 1024 * 1024;
		} else if (size.contains("GB")) {
			
			format = 1024 * 1024 * 1024;
		}
		size = size.substring(0, size.indexOf(" "));
		try {
			this.size = Double.parseDouble(size) * format;
		} catch (NumberFormatException e) {
			this.size = 0;
		}
		
	}

	public String getPathDirectory() {
		return pathDirectory;
	}

	public void setPathDirectory(String pathDirectory) {
		this.pathDirectory = pathDirectory;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}


}
