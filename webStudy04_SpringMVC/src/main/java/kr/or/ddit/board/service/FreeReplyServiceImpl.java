package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.FreeReplyDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.utils.PasswordUtils;
import kr.or.ddit.vo.FreeReplyVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class FreeReplyServiceImpl implements FreeReplyService {

	@Inject
	private FreeReplyDAO dao;
	
	private void encryptPassword(FreeReplyVO reply) {
		reply.setRepPass(PasswordUtils.encodePassword(reply.getRepPass()));
	}
	
	@Override
	public ServiceResult createReply(FreeReplyVO reply) {
		encryptPassword(reply);
		int rowcnt = dao.insertReply(reply);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public List<FreeReplyVO> retrieveReplyList(PagingVO<FreeReplyVO> pagingVO) {
		pagingVO.setTotalRecord(dao.selectTotalRecord(pagingVO));
		List<FreeReplyVO> replyList = dao.selectReplyList(pagingVO);
		pagingVO.setDataList(replyList);
		return replyList;
	}

	@Override
	public ServiceResult modifyReply(FreeReplyVO reply) {
		encryptPassword(reply);
		int rowcnt = dao.updateReply(reply);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.INVALIDPASSWORD;
	}

	@Override
	public ServiceResult removeReply(FreeReplyVO reply) {
		encryptPassword(reply);
		int rowcnt = dao.deleteReply(reply);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.INVALIDPASSWORD;
	}

}
