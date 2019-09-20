package pack;

public class lca<Key extends Comparable<Key>, Value>
{
    public Node root;

    public class Node
    {
        private Key key;
        private int value;
        private Node left, right;
        private int N;

        public Node(Key key, int value, int N)
        {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }
    
    // this creates our tree
    public void put(Key key, int value)
    {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, int value)
    {
        if(x == null)
        	return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
        	x.left  = put(x.left, key, value);
        else if(cmp > 0)
        	x.right = put(x.right, key, value);
        else
        	x.value = value;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }
    
    // return number of key-value pairs in BST
    public int size()
    {
    	return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x)
    {
        if (x == null)
        	return 0;
        else
        	return x.N;
    }
    
    // recursively navigates a tree from a supposed 'root' node to see if it has a certain descendant
    public boolean isAncestor(Node x, Node y)
    {
    	return isAncestorRecursive(x, y, false);
    }
    
    private boolean isAncestorRecursive(Node x, Node y, boolean answer)
    {
    	if(x.left != null)
    	{
    		if(x.left == y)
    			return true;
    		else
    			answer = isAncestorRecursive(x.left, y, false);
    	}
    	if(answer == true)
    		return true;
    	if(x.right != null)
    	{
    		if(x.right == y)
    			return true;
    		else
    			answer = isAncestorRecursive(x.right, y, false);
    	}
    	return answer;
    }
    
    public Node lowestCommonAncestor(Node list[])
    {
    	return lcaRecursive(list, root);
    }
    
    private Node lcaRecursive(Node list[], Node ancestor)
    {
    	boolean test = true;
    	Node newA = null;
    	for(int i = 0; i < list.length; i++)
    	{
    		if(isAncestor(ancestor, list[i]) != true)
    		{
    			test = false;
    		}
    	}
    	if(test = false)
    	{
    		return ancestor;
    	}else
    	{
    		newA = lcaRecursive(list, ancestor.left);
    		if(newA == ancestor.left)
    			newA = lcaRecursive(list, ancestor.right);
    		else
    			newA = lcaRecursive(list, ancestor.left);
    	}
    	return newA;
    }
    
    // delete these later leo
    public String printKeys()
    {
    	return printKeys(root, "");
    }
    
    private String printKeys(Node current, String prefix)
    {
    	String result = "";
        if(current == null)
        {
        	result += prefix + "-null\n";
        	return result;
        }
        result += prefix + "-" + current.key + "\n";
        result += printKeys(current.left, prefix + " |");
        result += printKeys(current.right, prefix + "  ");
    	return result;
    }
}
