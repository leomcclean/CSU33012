package pack;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class testing
{
	@Test
	void testSix_RootLCA()
	{
		lca tree = new lca();
		tree.root = tree.new Node(1);
		tree.root.right = tree.new Node(2);
		tree.root.right.right = tree.new Node(3);
		tree.root.right.right.left = tree.new Node(4);
		tree.root.left = tree.new Node(5);
		tree.root.left.right = tree.new Node(6);
		int[] array = {4,6};
		assertEquals(tree.lowestCommonAncestor(array), 1);
	}

	@Test
	void testSix_InLCA()
	{
		lca tree = new lca();
		tree.root = tree.new Node(1);
		tree.root.right = tree.new Node(2);
		tree.root.right.right = tree.new Node(3);
		tree.root.left = tree.new Node(5);
		tree.root.left.left = tree.new Node(4);
		tree.root.left.right = tree.new Node(6);
		int[] array = {4,6};
		assertEquals(tree.lowestCommonAncestor(array), 5);
	}

	@Test
	void testTwelve_RootLCA()
	{
		lca tree = new lca();
		tree.root = tree.new Node(1);
		tree.root.right = tree.new Node(2);
		tree.root.right.right = tree.new Node(3);
		tree.root.right.right.left = tree.new Node(4);
		tree.root.left = tree.new Node(5);
		tree.root.left.right = tree.new Node(6);
		tree.root.right.left = tree.new Node(7);
		tree.root.right.right.left.right = tree.new Node(8);
		tree.root.right.right.left.right.right = tree.new Node(9);
		tree.root.left.right.left = tree.new Node(10);
		tree.root.left.right.left.right = tree.new Node(11);
		tree.root.left.right.left.right.left = tree.new Node(12);
		int[] array = {2,3,11,12};
		assertEquals(tree.lowestCommonAncestor(array), 1);
	}
	
	@Test
	void testTwelve_InLCA()
	{
		lca tree = new lca();
		tree.root = tree.new Node(1);
		tree.root.right = tree.new Node(2);
		tree.root.right.right = tree.new Node(3);
		tree.root.right.right.left = tree.new Node(4);
		tree.root.left = tree.new Node(5);
		tree.root.left.right = tree.new Node(6);
		tree.root.right.left = tree.new Node(7);
		tree.root.right.right.left.right = tree.new Node(8);
		tree.root.right.right.left.left = tree.new Node(9);
		tree.root.left.right.left = tree.new Node(10);
		tree.root.left.right.left.right = tree.new Node(11);
		tree.root.left.right.left.right.left = tree.new Node(12);
		int[] array = {8,9,7};
		assertEquals(tree.lowestCommonAncestor(array), 2);
	}
}
