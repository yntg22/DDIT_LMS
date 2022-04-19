package kr.or.ddit.board.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;

@Mapper
public interface AttatchDAO {
	public int insertAttaches(FreeBoardVO board);
	
	public AttatchVO selectAttach(Integer attNo);
	public void incrementDowncount(Integer attNo);
	
	public int deleteAttaches(FreeBoardVO board);
}
