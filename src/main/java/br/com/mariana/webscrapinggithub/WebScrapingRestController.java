package br.com.mariana.webscrapinggithub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebScrapingRestController {

	public static final String GITHUB_URI = "v1/web-scraping/github";
	
	@Autowired
	private WebScrapingService webScrapingService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = GITHUB_URI)
	public Map<String, ResultDto> getWebScrapingGithub (@RequestParam(value = "user") String user, 
										@RequestParam(value = "repository") String repository) {
		
		HashMap<String, ResultDto> result = new HashMap<>();

		try {	 
		String path = user + "/" + repository;
		List<WebScrapingFiles> lisWebScrapingFiles = webScrapingService.getFilesByRepository(path);
				
		
			for(WebScrapingFiles webScrapingFiles: lisWebScrapingFiles) {
				if (result.containsKey(webScrapingFiles.getExtension())) {
					result.get(webScrapingFiles.getExtension()).countLines(webScrapingFiles.getLines());
					result.get(webScrapingFiles.getExtension()).countSize(webScrapingFiles.getSize());
					result.get(webScrapingFiles.getExtension()).addQuantity(); 
				} else {
					result.put(webScrapingFiles.getExtension(), new ResultDto(webScrapingFiles.getLines(), webScrapingFiles.getSize()));
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		
		return result;
	}
}
