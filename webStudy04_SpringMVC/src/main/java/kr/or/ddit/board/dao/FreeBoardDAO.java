package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.PagingVO;

@Mapper
public interface FreeBoardDAO {
	public int insertBoard(FreeBoardVO board);
	public int selectTotalRecord(PagingVO<FreeBoardVO> paging);
	public List<FreeBoardVO> selectBoardList(PagingVO<FreeBoardVO> paging);
	
	public FreeBoardVO selectBoard(Integer boNo);
	public void incrementHit(Integer boNo);
	
	public int updateBoard(FreeBoardVO board);
	public int deleteBoard(FreeBoardVO board);
	
}




