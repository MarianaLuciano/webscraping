package br.com.mariana.webscrapinggithub;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebScrapingService {
	
	public static final String URL = "https://github.com/";
	
	private List<WebScrapingFiles> listResultDTO = new ArrayList<>();
	
	@Cacheable("files")
	public List<WebScrapingFiles> getFilesByRepository(String path) {
		
		ArrayList<WebScrapingFiles> listFiles = new WebScrapingBody().getFiles(getByUri(path).getBody());
		
		organizeFiles(listFiles, "");
		
		return listResultDTO;
	}

	public WebScrapingBody getByUri(String path) {
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			URI uri = URI.create(URL + path); 
			return new WebScrapingBody(restTemplate.getForEntity(uri, String.class).getBody());
		} catch (Exception e) {
			e.getStackTrace();		
		}
		
		return null;
	}
	
	private void organizeFiles(ArrayList<WebScrapingFiles> listFiles, String pathDirectoryParent) {		
		for (WebScrapingFiles webScrapingFiles : listFiles) {
			if (webScrapingFiles.isDirectory()) {
				
				ArrayList<WebScrapingFiles> listFilesDirectory = new WebScrapingBody().getFiles(getByUri(webScrapingFiles.getPathDirectory()).getBody());
				
				organizeFiles(listFilesDirectory, webScrapingFiles.getPathDirectory());
				
			} else {
				if (!webScrapingFiles.getPathDirectory().isEmpty()) {
					WebScrapingBody webScrapingBody = getByUri(webScrapingFiles.getPathDirectory());
					
					if (webScrapingBody != null) {
						webScrapingFiles.setLines(webScrapingBody.getLines());
						webScrapingFiles.setSize(webScrapingBody.getSize());
					}
					
				} 
				
				listResultDTO.add(webScrapingFiles);

			} 
			
		}

	}

	
}