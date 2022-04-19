package kr.or.ddit.member.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@ContextHierarchy({
	@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")
	, @ContextConfiguration("file:webapp/WEB-INF/spring/appServlet/servlet-context.xml")
})
@WebAppConfiguration
@Slf4j
@Transactional
public class MemberDAOTest {
	@Inject
	private MemberDAO dao;
	
	@Test
	public void testSelectMember() {
		MemberVO member = dao.selectMember("v001");
		assertNotNull(member);
	}

	@Test
	public void testRealMemberDelete() {
		Map<String, Object> paramMap = new HashMap<>();
		dao.realMemberDelete(paramMap);
		Integer rowcnt = (Integer) paramMap.get("rowcnt");
		assertNotNull(rowcnt);
		assertNotEquals(0, rowcnt.intValue());
		log.info("탈퇴 인원수 : {}", rowcnt);
	}

}









