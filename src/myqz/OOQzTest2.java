package myqz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OOQzTest2 {

	private OOQz ot = new OOQz();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ot.initialize();
		ot.mungeData();
	}

	@Test
	public void TestAskQ0() {
	}
	
	@Test
	public void TestGotIt0() {
		ot.askQ(0);
		ot.GotIt(0);
		assertEquals(67, ot.getQuestions().get(0).getRating());
	}
	
}
