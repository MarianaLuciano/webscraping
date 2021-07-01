package br.com.mariana.webscrapinggithub;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

public class WebScrapingBody {
	
	private String body;

	public WebScrapingBody(String body) {
		this.body = body;
	}

	public WebScrapingBody() {
		// TODO Auto-generated constructor stub
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	
	public ArrayList<WebScrapingFiles> getFiles(String body) {
		String [] files = body.split("Box-row Box-row--focus-gray py-2 d-flex position-relative js-navigation-item");
		
		ArrayList<WebScrapingFiles> listOfFiles = new ArrayList<>();

		for (String file : files) {
			
			int hrefPos = file.indexOf("title=\"");
			int finalPos = file.indexOf("\" data-pjax", hrefPos);
			String fileName = file.substring(hrefPos+7, finalPos);
			
			if (!fileName.contains("https://github.com/fluidicon.png")) {
				String string = file.substring(hrefPos);
				String pathFile = string.substring(string.indexOf("href=\"/")+7, string.indexOf("\">"));
			
				listOfFiles.add(new WebScrapingFiles(fileName, isDirectory(file), pathFile));
			}
		}
		
		return listOfFiles;
	} 
	
	public int getLines() {
		int index = body.indexOf("text-mono f6 flex-auto pr-3 flex-order-2 flex-md-order-1");
		int index2 = body.indexOf("lines");
		try {
			if (index > 0)
				return Integer.valueOf(StringUtils.trimAllWhitespace(body.substring(index+66, index2)));
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return 0;
	}
	
	public String getSize() {
		int index = body.indexOf("<span class=\"file-info-divider\"></span>");
		String string = body.substring(index+44);
		
		int index2 = string.indexOf("</div>");
		
		return string.substring(0, index2-3);
	}
	
	
	private boolean isDirectory(String file) {
		return file.contains("Directory");
	}
}
