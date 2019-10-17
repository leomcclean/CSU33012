package pack;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

class testing
{

	@Test
	void badDAG()
	{
		dag test = new dag(-1);
		assertFalse(test.edge(0, 1));
	}
	
	@Test
	void badEdge()
	{
		dag test = new dag(2);
		assertFalse(test.edge(-1, 1) && test.edge(0, 3) && test.edge(0, -2) && test.edge(4, 1));
	}

	@Test
	void testLCA3()
	{
		dag test = new dag(3);
		test.edge(0, 1);
		test.edge(0, 2);
		assertEquals(test.lca(1, 2), 0);
	}

	@Test
	void testLCA1()
	{
		dag test = new dag(1);
		test.edge(0, 1);
		assertEquals(test.lca(0, 0), -1);
	}
	
	@Test
	void testLCA0()
	{
		dag test = new dag(0);
		assertEquals(test.lca(0, 0), -1);
	}

	@Test
	void testLCA5()
	{
		dag test = new dag(5);
		test.edge(0, 1);
		test.edge(0, 2);
		test.edge(1, 4);
		test.edge(2, 3);
		assertEquals(test.lca(3, 4), 0);
	}
}
