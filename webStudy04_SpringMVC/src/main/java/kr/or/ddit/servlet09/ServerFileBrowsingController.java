package kr.or.ddit.servlet09;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.vo.fancytree.FancytreeNode;
import kr.or.ddit.vo.fancytree.FancytreeNodeAdapter;

@Controller
@RequestMapping("/server/browsing.do")
public class ServerFileBrowsingController{
	
	@Inject
	private WebApplicationContext context;
	
	@GetMapping
	public String browsingView() {
		return "server/browsing";
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<FancytreeNode<File>> browsing(
		@RequestParam(value="currentPath", required=false, defaultValue="/") String currentPath
	){
		ServletContext application = context.getServletContext();
		Set<String> resourcePaths = application.getResourcePaths(currentPath);
		List<FancytreeNode<File>> dataList = new ArrayList<>();
		if(resourcePaths!=null)
			for(String path : resourcePaths) {
				String realPath = application.getRealPath(path);
				System.out.println(path);
				System.out.println(realPath);
				File adaptee = new File(realPath);
				dataList.add(new FancytreeNodeAdapter(adaptee, path));
			}
		
		Collections.sort(dataList);
		return dataList;			
	}
	
}
