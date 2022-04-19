package kr.or.ddit.board.view;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.vo.AttatchVO;

public class BoardDownloadView extends AbstractView{
	@Value("#{appInfo['attatchPath']}")
	File saveFolder;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse resp) throws Exception {
		AttatchVO attatch = (AttatchVO) model.get("attatch");
		
		File saveFile = new File(saveFolder, attatch.getAttSavename()); 
		String fileName = attatch.getAttFilename();
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
