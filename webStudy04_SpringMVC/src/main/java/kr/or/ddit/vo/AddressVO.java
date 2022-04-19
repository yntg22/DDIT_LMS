package kr.or.ddit.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validate.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressVO {
	@NotNull(groups=UpdateGroup.class)
	@Min(value=1, groups=UpdateGroup.class)
	private Integer addId;
	@NotBlank
	private String addName;
	@NotBlank
	private String addHp;
	@NotBlank
	private String address;
}












