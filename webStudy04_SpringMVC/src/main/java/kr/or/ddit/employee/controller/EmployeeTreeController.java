package kr.or.ddit.employee.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.employee.dao.EmployeeDAO;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.fancytree.FancytreeNode;
import kr.or.ddit.vo.fancytree.FancytreeNodeEmployeeWrapper;

@Controller
@RequestMapping("/employee/treeView.do")
public class EmployeeTreeController {
	
	private final EmployeeDAO empDAO;
	public EmployeeTreeController(EmployeeDAO empDAO) {
		super();
		this.empDAO = empDAO;
	}

	@GetMapping
	public String treeView() {
		return "employee/treeView";
	}

	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<FancytreeNode<EmployeeVO>> browsing(
		@RequestParam(value="managerId", required=false, defaultValue="") String managerId
	){
		List<EmployeeVO> adapteeList = empDAO.selectEmploeeList(managerId); 
		
		List<FancytreeNode<EmployeeVO>> dataList = new ArrayList<>();
		for(EmployeeVO adaptee : adapteeList) {
			dataList.add(new FancytreeNodeEmployeeWrapper(adaptee));
		}
		
		Collections.sort(dataList);
		
		return dataList;
			
	}
}
