package kr.or.ddit.vo;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;

public class MemberVOTest {
	private static Validator validator;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("before class");
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void test() {
		System.out.println("test case");
		MemberVO member = new MemberVO();
//		member.setMemId("a001");
		member.setMemPass("java");
		member.setMemMail("asdf");
		member.setMemBir("sdfasdf");
		member.setMemHp("000-000-0000");
		member.setMemHometel("0-000-0000");
		Set<ConstraintViolation<MemberVO>> errors = validator.validate(member, InsertGroup.class, UpdateGroup.class);
		boolean valid = errors.isEmpty();
		System.out.println(valid);
		if(!valid) {
			for(ConstraintViolation<MemberVO>  propError : errors) {
				Path key = propError.getPropertyPath();
				String value = propError.getMessage();
				System.out.printf("%s : %s\n", key, value);
			}
		}
	}

}












