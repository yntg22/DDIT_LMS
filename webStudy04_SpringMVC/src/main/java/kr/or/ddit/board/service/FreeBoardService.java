package kr.or.ddit.board.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.PagingVO;

public interface FreeBoardService {
	public ServiceResult createBoard(FreeBoardVO board);
	public void retrieveBoardList(PagingVO<FreeBoardVO> paging);
	public FreeBoardVO retrieveBoard(Integer boNo);
	public ServiceResult  modifyBoard(FreeBoardVO board);
	public ServiceResult removeBoard(FreeBoardVO board);
	
	public AttatchVO downloadAttatch(Integer attNo);
}
