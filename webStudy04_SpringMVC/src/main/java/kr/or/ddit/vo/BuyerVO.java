package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="buyerId")
public class BuyerVO implements Serializable{
	private int rnum;
	private int prodCnt;
	@NotBlank(groups=UpdateGroup.class)
	private String buyerId;
	@NotBlank
	private String buyerName;
	@NotBlank
	private String buyerLgu;
	private String lprodNm;
	private String buyerBank;
	private String buyerBankno;
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	@NotBlank
	private String buyerComtel;
	@NotBlank
	private String buyerFax;
	@NotBlank
	private String buyerMail;
	private String buyerCharger;
	private String buyerTelext;
	
	private Set<ProdVO> prodList;
}
