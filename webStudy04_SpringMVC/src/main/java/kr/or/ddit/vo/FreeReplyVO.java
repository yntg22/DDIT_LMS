package kr.or.ddit.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="repNo")
public class FreeReplyVO implements Serializable{
	private int rnum;
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	private Integer repNo;
	@NotNull
	private Integer boNo;
	private String repContent;
	@NotBlank
	private String repWriter;
	private String repMail;
	@NotBlank(groups= {Default.class, DeleteGroup.class})
	private String repPass;
	private String repDate;
}
