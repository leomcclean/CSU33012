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
	void testCyclic()
	{
		dag test = new dag(3);
		assertFalse(!test.edge(0, 1) && !test.edge(1, 2) && test.edge(2, 3));
	}

	@Test
	void testTwelveNodeLCA()
	{

	}
}
