package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 거래처 관리 Persistence Layer
 *
 */
@Mapper
public interface BuyerDAO {
	public int selectTotalRecord(PagingVO<BuyerVO> pagingVO);
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO);
	public BuyerVO selectBuyer(String buyer_id);
	public int insertBuyer(BuyerVO buyer);
	public int updateBuyer(BuyerVO buyer);
}
