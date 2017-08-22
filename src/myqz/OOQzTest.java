package myqz;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OOQzTest {

	private OOQz ot = new OOQz();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInitialize() {
		ot.initialize();
		assertEquals("", ot.getNotes());
		assertEquals("0", ot.getqCorrect());
		assertEquals("3", ot.getqCount());
		assertEquals("0", ot.getqCount2());
		assertEquals("1503420794", ot.getSessionStart());
		assertEquals("0", ot.getTotalRating());
		assertEquals("0", ot.getTotalTime());
		
		assertEquals(0, ot.getErrors().size());
		assertEquals(1, ot.getFileNumDirty().size());
		assertEquals(1, ot.getFileNumFileName().size());
		assertEquals(1, ot.getFileNumQuestionCount().size());
		assertEquals(0, ot.getQByAge().size());
		assertEquals(0, ot.getQByRating().size());
		assertEquals(18, ot.getQData().size());
		assertEquals(0, ot.getRatingTree().size());
		assertEquals(0, ot.getWhenAsked().size());
		
		assertOneElement(ot.getFileNumDirty(), "0");
		assertOneElement(ot.getFileNumFileName(), "mystuff.qz");
		assertOneElement(ot.getFileNumQuestionCount(), "3");

		String[] expected = {
			"AQT"	,
			"QAT"	,
			"100"	,
			"0"	,
			"CO",	
			""	,
			"IQS",	
			"QIS",	
			"100",	
			"0"	,
			"CO",	
			""	,
			"AEU",	
			"EAU",	
			"100",	
			"0"	,
			"CO",	
			""
		};
		assertListContains(ot.getQData(), expected);
	}
	@Test
	public void testMungeData() {
		ot.initialize();
		ot.mungeData();
		assertEquals("4", ot.getqCount2());
		assertEquals("300", ot.getTotalRating());
		
		assertEquals(3, ot.getQByAge().size());
		assertEquals(102, ot.getQByRating().size());
		assertEquals(3, ot.getRatingTree().size());
		assertEquals(3, ot.getWhenAsked().size());
		
		assertOneElement(ot.getFileNumDirty(), "0");
		assertOneElement(ot.getFileNumFileName(), "mystuff.qz");
		assertOneElement(ot.getFileNumQuestionCount(), "3");

		String[] expected = {
			"AQT"	,
			"QAT"	,
			"100"	,
			"0"	,
			"CO",	
			""	,
			"IQS",	
			"QIS",	
			"100",	
			"0"	,
			"CO",	
			""	,
			"AEU",	
			"EAU",	
			"100",	
			"0"	,
			"CO",	
			""
		};
		assertListContains(ot.getQData(), expected);
		
		String[] expQByAge = {"0", "1", "2"};
		assertListContains(ot.getQByAge(), expQByAge);
		
		String qByRating100 = "0 1 2 ";
		assertEquals(qByRating100, ot.getQByRating().get(100));

		String[] expRatingTree = {"100", "200", "100"};
		assertListContains(ot.getRatingTree(), expRatingTree);
		
		String[] expWhenAsked = {"-15", "-15", "-15"};
		assertListContains(ot.getWhenAsked(), expWhenAsked);
		
	}
	@Test
	public void TestAskQ0() {
		ot.initialize();
		ot.mungeData();
		ot.askQ(0);
		String[] expWhenAsked = {"1", "-15", "-15"};
		assertListContains(ot.getWhenAsked(), expWhenAsked);
	}
	
	@Test
	public void TestGotIt0() {
		ot.initialize();
		ot.mungeData();
		ot.askQ(0);
		ot.GotIt(0);
		assertEquals("1", ot.getqCorrect());
		assertEquals("269", ot.getTotalRating());
		assertEquals("7.55555555555556", ot.getTotalTime());
		assertEquals("1", ot.getFileNumDirty().get(0));

		String[] expQByAge = {"1", "2", "0"};
		assertListContains(ot.getQByAge(), expQByAge);
	
		// element 69 now contains 0
		// element 100 now contains "1 2 " (sic)
		assertEquals("0", ot.getQByRating().get(69));
		assertEquals("1 2 ", ot.getQByRating().get(100));
		
		assertEquals("69", ot.getQData().get(2));
		assertEquals("1503427718", ot.getQData().get(3));

		String[] expRatingTree = {"69", "169", "100"};
		assertListContains(ot.getRatingTree(), expRatingTree);
		
		assertEquals(67, ot.getQuestions().get(0).getRating());
	}
	
	
	@Test
	public void TestAskQ1() {
		ot.initialize();
		ot.mungeData();
		ot.askQ(0);
		ot.GotIt(0);
		ot.askQ(1);
		String[] expWhenAsked = {"1", "2", "-15"};
		assertListContains(ot.getWhenAsked(), expWhenAsked);
	}
	
	@Test
	public void TestGotIt1() {
		ot.initialize();
		ot.mungeData();
		ot.askQ(0);
		ot.GotIt(0);
		ot.askQ(1);
		ot.GotIt(1);
		assertEquals("2", ot.getqCorrect());
		assertEquals("237", ot.getTotalRating());
		assertEquals("12.1111111111111", ot.getTotalTime());

		String[] expQByAge = {"2", "0", "1"};
		assertListContains(ot.getQByAge(), expQByAge);
	
		// element 68 now contains 1
		// element 100 now contains "1 2 " (sic)
		assertEquals("1", ot.getQByRating().get(68));
		assertEquals("2 ", ot.getQByRating().get(100));
	
		assertEquals("68", ot.getQData().get(8));
		assertEquals("1503430295", ot.getQData().get(9));

		String[] expRatingTree = {"69", "137", "100"};
		assertListContains(ot.getRatingTree(), expRatingTree);
	}
	
	@Test
	public void TestAskQ2() {
		ot.initialize();
		ot.mungeData();
		ot.askQ(0);
		ot.GotIt(0);
		ot.askQ(1);
		ot.GotIt(1);
		ot.askQ(2);
		String[] expWhenAsked = {"1", "2", "3"};
		assertListContains(ot.getWhenAsked(), expWhenAsked);
	}
	@Test
	public void TestGotIt2() {
		ot.initialize();
		ot.mungeData();
		ot.askQ(0);
		ot.GotIt(0);
		ot.askQ(1);
		ot.GotIt(1);
		ot.askQ(2);
		ot.GotIt(2);
		
		assertEquals("3", ot.getqCorrect());
		assertEquals("205", ot.getTotalRating());
		assertEquals("15.6666666666667", ot.getTotalTime());

		String[] expQByAge = {"0", "1", "2"};
		assertListContains(ot.getQByAge(), expQByAge);
	
		// element 68 now contains "1 2 " (sic)
		// element 100 now contains ""
		assertEquals("1 2 ", ot.getQByRating().get(68));
		assertEquals("", ot.getQByRating().get(100));

		assertEquals("68", ot.getQData().get(14));
		assertEquals("1503431952", ot.getQData().get(15));

		String[] expRatingTree = {"69", "137", "68"};
		assertListContains(ot.getRatingTree(), expRatingTree);

		assertEquals(67, ot.getQuestions().get(1).getRating());
	
	}

	private void assertOneElement(List<String> list, String expected) {
		assertEquals(list.get(0), expected);
	}
	
	private void assertListContains(List<String> list, String[] expected) {
		assertEquals(expected.length, list.size());
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], list.get(i));
		}
	}

}
