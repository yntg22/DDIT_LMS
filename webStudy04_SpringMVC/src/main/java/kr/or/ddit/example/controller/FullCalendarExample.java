package kr.or.ddit.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.employee.dao.EmployeeDAO;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.fullcalendar.FullCalendarEventEmployeeWrapper;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/example/fullCalendar")
@AllArgsConstructor
public class FullCalendarExample {
	
	private final EmployeeDAO empDAO;

	@RequestMapping
	public String exampleView() {
		return "example/fullCalendar";
	}
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<FullCalendarEventEmployeeWrapper> eventData(){
		List<EmployeeVO> empList = empDAO.selectAllEmployee();
		List<FullCalendarEventEmployeeWrapper> eventList = new ArrayList<>(empList.size());
		for(EmployeeVO adaptee : empList) {
			eventList.add(new FullCalendarEventEmployeeWrapper(adaptee));
		}
		return eventList;
	}
}
