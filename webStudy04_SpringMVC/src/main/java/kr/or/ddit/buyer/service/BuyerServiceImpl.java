package kr.or.ddit.buyer.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class BuyerServiceImpl implements BuyerService{
	
	@Inject
	private BuyerDAO buyerDAO;
	
	@Override
	public List<BuyerVO> retrieveBuyerList(PagingVO<BuyerVO> pagingVO) {
		pagingVO.setTotalRecord(buyerDAO.selectTotalRecord(pagingVO));
		List<BuyerVO> buyerList = buyerDAO.selectBuyerList(pagingVO);
		pagingVO.setDataList(buyerList);
		return buyerList;
	}

	@Override
	public BuyerVO retrieveBuyer(String buyer_id) {
		BuyerVO buyer = buyerDAO.selectBuyer(buyer_id);
		if(buyer==null) throw new PKNotFoundException("없음.");
		return buyer;
	}

	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		int rowcnt = buyerDAO.insertBuyer(buyer);
		ServiceResult result = ServiceResult.OK;
		if(rowcnt<= 0) result = ServiceResult.FAIL;
		return result;
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyer) {
		retrieveBuyer(buyer.getBuyerId());
		int rowcnt = buyerDAO.updateBuyer(buyer);
		ServiceResult result = ServiceResult.OK;
		if(rowcnt <= 0) result = ServiceResult.FAIL;
		return result;
	}

}
