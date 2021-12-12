package parallelscripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ClassTwoTest {
	@BeforeClass
	public void setUp() {
		long id = Thread.currentThread().getId();
		System.out.println("Before in ClassTwoTest : " + id);
	}

	@Test
	public void methodOne() {
		long id = Thread.currentThread().getId();
		System.out.println("MoethodONe in ClassTwoTest : " + id);
	}

	@Test
	public void methodTwo() {
		long id = Thread.currentThread().getId();
		System.out.println("MoethodTwo in ClassTwoTest : " + id);
	}

	@Test
	public void methodThree() {
		long id = Thread.currentThread().getId();
		System.out.println("MoethodThree in ClassTwoTest : " + id);
	}

	@AfterClass
	public void tearDown() {
		long id = Thread.currentThread().getId();
		System.out.println("After in ClassTwoTest : " + id);
	}

}
