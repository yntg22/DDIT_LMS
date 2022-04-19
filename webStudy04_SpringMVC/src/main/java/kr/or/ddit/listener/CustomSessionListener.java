package kr.or.ddit.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import kr.or.ddit.vo.MemberVO;

@WebListener
public class CustomSessionListener implements HttpSessionListener, HttpSessionAttributeListener {


    public void sessionCreated(HttpSessionEvent se)  { 
    	ServletContext application = se.getSession().getServletContext();
    	int userCount = (Integer) application.getAttribute("usercount");
    	application.setAttribute("usercount", userCount + 1);
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    }

    public void attributeAdded(HttpSessionBindingEvent event)  { 
    	String attName = event.getName();
    	if("authMember".equals(attName)) {
    		ServletContext application = event.getSession().getServletContext();
    		Set<MemberVO> userList = (Set) application.getAttribute("userList");
    		userList.add((MemberVO)event.getValue());
    	}
    }

    public void attributeRemoved(HttpSessionBindingEvent event)  { 
    	String attName = event.getName();
    	if("authMember".equals(attName)) {
    		ServletContext application = event.getSession().getServletContext();
    		Set<MemberVO> userList = (Set) application.getAttribute("userList");
    		userList.remove((MemberVO)event.getValue());
    	}
    }

    public void attributeReplaced(HttpSessionBindingEvent event)  { 
    }
	
}
