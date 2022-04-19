package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.FreeBoardDAO;
import kr.or.ddit.board.dao.FreeReplyDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.utils.PasswordUtils;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

	@Inject
	private FreeBoardDAO boardDAO;
	@Inject
	private AttatchDAO attatchDAO;
	@Value("#{appInfo.attatchPath}")
	private File saveFolder;
	
	@Inject
	private FreeReplyDAO replyDAO;
	
	@Override
	public ServiceResult createBoard(FreeBoardVO board) {
		String plain = board.getBoPass();
		board.setBoPass(PasswordUtils.encodePassword(plain));
		int rowcnt = boardDAO.insertBoard(board);
		if(rowcnt > 0) {
			uploadAttatches(board);
		}
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public void retrieveBoardList(PagingVO<FreeBoardVO> paging) {
		int totalRecord = boardDAO.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<FreeBoardVO> dataList = boardDAO.selectBoardList(paging);
		paging.setDataList(dataList);
	}

	@Override
	public FreeBoardVO retrieveBoard(Integer boNo) {
		FreeBoardVO board = boardDAO.selectBoard(boNo);
		
		if(board==null)
			throw new PKNotFoundException(String.format("%d 번 글이 없음.", boNo));
		
		boardDAO.incrementHit(boNo);		
		return board;
	}
	
	private boolean authenticate(FreeBoardVO input, FreeBoardVO saved) {
		return PasswordUtils.passwordMatcher(input.getBoPass(), saved.getBoPass());
	}

	private void uploadAttatches(FreeBoardVO board) {
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList==null || attatchList.isEmpty()) return;
//		업로드 : 2진 데이터(saveFolder) + 메타데이터(Attatch)
		
		attatchDAO.insertAttaches(board);
		attatchList.forEach((attatch)->{
			try {
				attatch.saveTo(saveFolder);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private void deleteAttatches(FreeBoardVO board) {
		int[] delAttNos = board.getDelAttNos();
		if(delAttNos==null || delAttNos.length==0) return;
//		삭제 : 2진 데이터(saveFolder) + 메타데이터(Attatch)
		List<String> saveNames = Arrays.stream(delAttNos).mapToObj((delAttNo)->{
									return attatchDAO.selectAttach(delAttNo).getAttSavename();
								}).collect(Collectors.toList());
		attatchDAO.deleteAttaches(board);
		saveNames.forEach((saveName)->{
			FileUtils.deleteQuietly(new File(saveFolder, saveName));
		});
	}
	
	@Override
	public ServiceResult modifyBoard(FreeBoardVO board) {
		FreeBoardVO saved = boardDAO.selectBoard(board.getBoNo());
		ServiceResult result = null;
		//** 인증
		if(authenticate(board, saved)) {
			//1. 게시글 수정
			int rowcnt = boardDAO.updateBoard(board);
			if(rowcnt > 0) {
				//2. 기존 파일 삭제
				deleteAttatches(board);
				//3. 신규 업로드
				uploadAttatches(board);
				
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public ServiceResult removeBoard(FreeBoardVO board) {
		FreeBoardVO saved = boardDAO.selectBoard(board.getBoNo());
		ServiceResult result = null;
		if(authenticate(board, saved)) {
		//=============================덧글 삭제
			replyDAO.deleteReplyAll(board);
			
			List<AttatchVO> attatchList = saved.getAttatchList();
		//=============================첨부 파일 삭제
			int[] delAttNos = attatchList.stream().mapToInt((attatch)->attatch.getAttNo())
												  .toArray();
			board.setDelAttNos(delAttNos);
			
			deleteAttatches(board);
		//========================================
			
			int rowcnt = boardDAO.deleteBoard(board);
			
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public AttatchVO downloadAttatch(Integer attNo) {
		AttatchVO attatch = attatchDAO.selectAttach(attNo);
		if(attatch==null)
			throw new PKNotFoundException(String.format("%d 번 파일이 없음.", attNo));
		attatchDAO.incrementDowncount(attNo);
		return attatch;
	}
}
