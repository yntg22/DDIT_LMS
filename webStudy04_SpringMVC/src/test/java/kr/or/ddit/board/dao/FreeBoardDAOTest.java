package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.member.dao.MemberDAOTest;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchVO;
import lombok.extern.slf4j.Slf4j;
@RunWith(SpringRunner.class)
@ContextHierarchy({
	@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")
	, @ContextConfiguration("file:webapp/WEB-INF/spring/appServlet/servlet-context.xml")
})
@WebAppConfiguration
@Transactional
@Slf4j
public class FreeBoardDAOTest {
	
	@Inject
	private FreeBoardDAO dao;
	private PagingVO<FreeBoardVO> paging;
	
	@Before
	public void before() {
		paging = new PagingVO<>();
		paging.setCurrentPage(1);
		paging.setSimpleCondition(new SimpleSearchVO());
	}

	@Test
	public void testInsertBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectTotalRecord() {
		int totalRecord = dao.selectTotalRecord(paging);
		assertNotEquals(0, totalRecord);
	}

	@Test
	public void testSelectBoardList() {
		List<FreeBoardVO> dataList = dao.selectBoardList(paging);
		assertNotEquals(0, dataList.size());
	}

	@Test
	public void testSelectBoard() {
		FreeBoardVO board = dao.selectBoard(23);
		assertNotNull(board);
	}

	@Test
	public void testUpdateBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBoard() {
		fail("Not yet implemented");
	}

}
