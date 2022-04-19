package kr.or.ddit.member.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import kr.or.ddit.member.dao.MemberDAO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class MemberDeleteJob {
	
	private final MemberDAO dao;
	
	@Schedules({
		@Scheduled(cron="0 0 3 * * MON")
//		, @Scheduled(initialDelay=2000, fixedRate=3000)
	})
	public void deleteJob() {
		Map<String, Object> paramMap = new HashMap<>();
		dao.realMemberDelete(paramMap);
		Integer rowcnt =(Integer) paramMap.get("rowcnt");
		log.info("탈퇴 인원 : {}", rowcnt);
		
	}
}














