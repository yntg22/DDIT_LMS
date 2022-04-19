package kr.or.ddit.address.dao;

import java.util.List;

import kr.or.ddit.vo.AddressVO;

public interface AddressDAO {
	public List<AddressVO> selectAddressList();
	public AddressVO selectAddress(Integer addId);
	public int insertAddress(AddressVO address);
}
