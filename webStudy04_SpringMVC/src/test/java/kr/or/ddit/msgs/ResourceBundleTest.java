package kr.or.ddit.msgs;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.BeforeClass;
import org.junit.Test;

public class ResourceBundleTest {

	@Test
	public void test() {
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.msgs.message", Locale.ENGLISH);
		String message = bundle.getString("hi");
		System.out.println(message);
	}

}
