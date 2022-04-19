package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.FreeReplyVO;
import kr.or.ddit.vo.PagingVO;

public interface FreeReplyService {
	public ServiceResult createReply(FreeReplyVO reply);
	public List<FreeReplyVO> retrieveReplyList(PagingVO<FreeReplyVO> pagingVO);
	public ServiceResult modifyReply(FreeReplyVO reply);
	public ServiceResult removeReply(FreeReplyVO reply);
}
