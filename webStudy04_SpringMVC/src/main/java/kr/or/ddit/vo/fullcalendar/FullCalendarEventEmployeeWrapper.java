package kr.or.ddit.vo.fullcalendar;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import kr.or.ddit.vo.EmployeeVO;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FullCalendarEventEmployeeWrapper implements FullCalendarEvent<EmployeeVO> {
	
	private final EmployeeVO adaptee;
	public FullCalendarEventEmployeeWrapper(EmployeeVO adaptee) {
		super();
		this.adaptee = adaptee;
	}
	@Override
	public String getId() {
		return adaptee.getEmployeeId().toString();
	}
	@Override
	public boolean isAllDay() {
		return true;
	}
	@Override
	public Date getStart() {
		return adaptee.getHireDate();
	}
	@Override
	public Date getEnd() {
		return adaptee.getHireDate();
	}
	@Override
	public String getStartStr() {
		return adaptee.getHireDateStr();
	}
	@Override
	public String getEndStr() {
		return adaptee.getHireDateStr();
	}
	@Override
	public String getTitle() {
		return adaptee.getEmpName();
	}
	@Override
	public EmployeeVO getOriginData() {
		return adaptee;
	}
	
	
}
