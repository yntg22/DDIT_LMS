package kr.or.ddit.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.EmployeeVO;

@Mapper
public interface EmployeeDAO {
	public List<EmployeeVO> selectEmploeeList(@Param("managerId") String managerId);
	public List<EmployeeVO> selectAllEmployee();
}
