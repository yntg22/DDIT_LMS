package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ImageUploadController {
	
	@Inject
	private WebApplicationContext context;
	
	@Value("#{appInfo['boardImages']}")
	private String saveFolderURL;
	private File saveFolder;
	
	@PostConstruct
	public void init() throws IOException {
		Resource saveFolderRes = context.getResource(saveFolderURL);
		saveFolder = saveFolderRes.getFile();
		if(!saveFolderRes.exists()) {
			saveFolder.mkdirs();
		}
		log.info("게시글 이미지 저장 위치, {}", saveFolder);
	}
	
	
	@PostMapping("/board/image")
	@ResponseBody
	public Map<String, Object> upload(@RequestPart MultipartFile upload) throws IllegalStateException, IOException {
//		1. classpath res 2. filesystem res 3. web res
		Map<String, Object> target = new HashMap<>();
		if(!upload.isEmpty()) {
			String saveName = UUID.randomUUID().toString();
			upload.transferTo(new File(saveFolder, saveName));
			String fileName = upload.getOriginalFilename();
			int uploaded = 1;
			String cPath = context.getServletContext().getContextPath();
			String url = cPath + saveFolderURL + "/" + saveName;
			target.put("fileName", fileName);
			target.put("uploaded", uploaded);
			target.put("url", url);
		}else {
			target.put("error", 400);
		}
		return target;
	}
}










