package kr.or.ddit.vo.fullcalendar;

import java.io.Serializable;
import java.util.Date;

public interface FullCalendarEvent<T> extends Serializable{
	public String getId();
	public boolean isAllDay();
	public Date getStart();
	public Date getEnd();
	public default String getStartStr() {
		return "";
	}
	public default String getEndStr() {
		return "";
	}
	public String getTitle();
	public default String getUrl() {
		return "";
	}
	public default String[] getClassNames() {
		return null;
	}
	public default Boolean isEditable() {
		return false;
	}
	public default Boolean isStartEditable() {
		return true;
	}
	public default Boolean isDurationEditable() {
		return true;
	}
	public default Boolean isResourceEditable() {
		return false;
	};
	public default String getDisplay() {
		return "auto";
	};
	public default Boolean isOverlap() {
		return true;
	}
	public default String backgroundColor() {
		return null;
	}
	public default String textColor() {
		return null;
	}
	public T getOriginData();
}
