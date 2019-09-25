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
    
    // recursively navigates a tree to create a lineage
    private int[] listAncestors(Node y)
    {
    	String roughLineage = listAncestorsRecursive(root, y, "").substring(1) + "." + root;
    	return sire(roughLineage);
    }
    
    private String listAncestorsRecursive(Node x, Node y, String answer)
    {
    	if(x.left != null)
    	{
    		if(x.left != y)
    			answer = listAncestorsRecursive(x.left, y, answer);
    		else
    			return answer + "." + x.left.value;
    	}
    	if(x.right != null)
    	{
    		if(x.right != y)
    			answer = listAncestorsRecursive(x.right, y, answer);
    		else
    			return answer + "." + x.right.value;
    	}
    	if(answer == "")
    		return answer;
    	else
    		return answer + "." + x.value;
    }
    
    // turns a delimited lineage String into a lineage Array
    private int[] sire(String roughLineage)
    {
    	int lineage[] = new int[10];
    	return lineage;
    }
    
    // find the lowest common ancestor by comparing lineages
    public Node lowestCommonAncestor(Node list[])
    {
    	Node lowest = root;
    	//int lineages[] = new int[list.length];
    	for(int i = 0; i < list.length; i++)
    	{
    		//lineages[i] = listAncestors(list[i]);
    	}
    	int shortest = 0;
    	//for(int i = 0; i < lineages.length; i++)
    	{
    		
    	}
    	for(int i = 1; i < shortest; i++)
    	{
    		
    	}
    	return lowest;
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
