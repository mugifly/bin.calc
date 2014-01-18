package info.ohgita.android.bincalc.calculator;

import junit.framework.TestCase;

public class ExpParserTest extends TestCase {
	public void testParse() throws Exception {
		ExpParser parser = new ExpParser();
		assertEquals("[1, +, 2]", parser.parseToList("1+2").toString());
		assertEquals("[(, 3, *, 2, )]", parser.parseToList("3*2").toString());
		assertEquals("[1, +, (, 3, /, 20, )]", parser.parseToList("1+3/20").toString());
	}
}