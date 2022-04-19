package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.FreeReplyVO;
import kr.or.ddit.vo.PagingVO;

/**
 * FreeReply 테이블을 대상으로 한 CRUD
 *
 */
@Mapper
public interface FreeReplyDAO {
	public int insertReply(FreeReplyVO reply);
	public int selectTotalRecord(PagingVO<FreeReplyVO> pagingVO);
	public List<FreeReplyVO> selectReplyList(PagingVO<FreeReplyVO> pagingVO);
	public int updateReply(FreeReplyVO reply);
	public int deleteReply(FreeReplyVO reply);
	public int deleteReplyAll(FreeBoardVO board);
}









