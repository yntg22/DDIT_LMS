package kr.or.ddit.fileupload.view;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model
			, HttpServletRequest request,
			HttpServletResponse resp) throws Exception {
		
		File saveFile = (File) model.get("saveFile");
		
		if(!saveFile.exists()) {
			resp.sendError(404, saveFile.getName()+"에 해당하는 파일이 없음.");
			return;
		}
		
		HttpSession session = request.getSession();
		Map<String, Object> fileMetaData = 
				(Map) session.getAttribute("uploadFile");
		
		String fileName = (String) fileMetaData.get("originalFilename");
		fileName = URLEncoder.encode(fileName, "UTF-8");
		fileName = fileName.replace("+", " ");
		resp.setHeader("Content-Disposition", "attatchment;filename=\""+fileName+"\"");
		resp.setHeader("Content-Length", saveFile.length()+"");
		try(
			OutputStream os = resp.getOutputStream();	
		){
			FileUtils.copyFile(saveFile, os);
			return;
		}
	}

}
