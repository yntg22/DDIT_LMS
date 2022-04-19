package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="boNo")
@ToString(exclude= {"boContent", "attatchList"})
public class FreeBoardVO {
	private int rnum;
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	private Integer boNo;
	@NotBlank
	private String boTitle;
	@NotBlank
	private String boWriter;
	@NotBlank
	private String boIp;
	private String boMail;
	@NotBlank(groups= {Default.class, DeleteGroup.class})
	private String boPass;
	private String boContent;
	private String boDate;
	private Integer boRep;
	private Integer boHit;
	private Integer boRec;
	
	@JsonIgnore
	private transient int startAttNo;
	
	@JsonIgnore
	private transient List<AttatchVO> attatchList;
	
	@JsonIgnore
	private transient int[] delAttNos;
	
	@JsonIgnore
	private transient MultipartFile[] boFiles;
	public void setBoFiles(MultipartFile[] boFiles) {
		if(boFiles==null || boFiles.length==0) return;
		this.boFiles = boFiles;
		this.attatchList = new ArrayList<>();
		for( MultipartFile eachFile : boFiles) {
			if(eachFile.isEmpty()) continue;
			AttatchVO attatch = new AttatchVO(eachFile);
			attatchList.add(attatch);
		}
	}
}










