package kr.or.ddit.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FileController {
	private String saveFolderPath = "D:\\saveFolder";
	
	@RequestMapping(value="/12/fileUpload_Framework.do", method=RequestMethod.POST)
	public String fileUpload(
		@RequestParam("uploader") String uploader
		, @RequestPart MultipartFile uploadFile
		, RedirectAttributes redirectAttributes
		, HttpSession session 
	) throws IOException {
//		uploader, uploadFile
		redirectAttributes.addFlashAttribute("uploader", uploader);
		Map<String, Object> fileMetaData = fileUpload(uploadFile);
		log.info("file meta data : {}", fileMetaData);
		session.setAttribute("uploadFile", fileMetaData);
		return "redirect:/12/fileUploadForm.jsp";
	}
	
	@RequestMapping("/12/fileDownload.do")
	public String fileDownload(
		@RequestParam("file") String file
		, Model model
	){
		File saveFolder = new File(saveFolderPath);
		File saveFile = new File(saveFolder, file);
		model.addAttribute("saveFile", saveFile);
		return "downloadView";
	}
	
	private Map<String, Object> fileUpload(MultipartFile uploadFilePart) throws IOException {
		Map<String, Object> metaData = new HashMap<>();
		String originalFilename = uploadFilePart.getOriginalFilename();
		File saveFolder = new File(saveFolderPath);
		String saveName = UUID.randomUUID().toString();
		File saveFile = new File(saveFolder, saveName);
		long fileSize = uploadFilePart.getSize();
		String fileMime = uploadFilePart.getContentType();
		metaData.put("originalFilename", originalFilename);
		metaData.put("saveName", saveName);
		metaData.put("fileSize", fileSize);
		metaData.put("fileMime", fileMime);
		
		uploadFilePart.transferTo(saveFile);
		
		return metaData;
	}
}














