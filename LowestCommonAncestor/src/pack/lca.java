package pack;

public class lca
{
    public Node root;

    public class Node
    {
        private int key;
        private int value;
        private Node left, right;
        private int N;

        public Node(int key, int value, int N)
        {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }
    
    // return number of int-value pairs in BST
    public int size()
    {
    	return size(root);
    }

    // return number of int-value pairs in BST rooted at x
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
    	String roughLineage = listAncestorsRecursive(root, y, "") + "." + root;
    	return sire(roughLineage);
    }
    
    private String listAncestorsRecursive(Node x, Node y, String answer)
    {
    	if(x.left != null && answer == "")
    	{
    		if(x.left != y)
    			answer = listAncestorsRecursive(x.left, y, answer);
    		else
    			return answer + "." + x.left.value;
    	}
    	if(x.right != null && answer == "")
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
    	int stringlength = roughLineage.length();
    	int lineage[] = new int[stringlength / 2];
    	for(int i = 1, j = 0; i <= stringlength; i += 2, j++)
    	{
    		lineage[j] = roughLineage.charAt(i);
    	}
    	return lineage;
    }
    
    // find the lowest common ancestor by comparing lineages
    public int lowestCommonAncestor(Node list[])
    {
    	int lineages[][] = new int[list.length][];
    	for(int i = 0; i < list.length; i++)
    	{
    		lineages[i] = listAncestors(list[i]);
    	}
    	
    	int shortest = 0;
    	for(int i = 0; i < lineages.length; i++)
    	{
    		if(lineages[i][0] < shortest || shortest == 0)
    			shortest = lineages[0][i];
    	}
    	
    	int lca = 0;
    	for(int i = 1; i < shortest; i++)
    	{
    		boolean test1 = true;
    		for(int j = 1; j < lineages.length; j++)
    		{
    			boolean test2 = false;
    			for(int k = 0; k < lineages[j].length; k++)
    			{
    				if(lineages[j][k] == lineages[0][i])
    					test2 = true;
    			}
    			if(!test2)
    			{
    				test1 = false;
    				break;
    			}
    		}
    		if(test1)
    		{
    			lca = lineages[0][i];
    			break;
    		}
    	}
    	return lca;
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
