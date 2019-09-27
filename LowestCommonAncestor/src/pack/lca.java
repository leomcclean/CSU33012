package pack;

import java.util.Arrays;

public class lca
{
    public Node root;

    public class Node
    {
        private int value;
        public Node left, right;

        public Node(int value)
        {
            this.value = value;
        }
    }
    
    // recursively navigates a tree to create a lineage
    private int[] listAncestors(int y)
    {
    	String roughLineage = listAncestorsRecursive(root, y, "") + ".";
    	return sire(roughLineage);
    }
    
    // turns a delimited lineage String into a lineage Array
    private int[] sire(String roughLineage)
    {
    	// this is all 'bad' code, using an ArrayList or something similar was the correct solution
    	int stringLength = roughLineage.length();
    	int lineage[] = new int[stringLength / 2];
    	int ancestor = 0;
    	for(int i = 1, j = 0, k = 0; i < stringLength; i++)
    	{
    		// convert the multi-digit numbers nicely from chars to a single int
    		char extract = roughLineage.charAt(i);
    		if(extract != '.')
    		{
    			if(k >= 1)
    				ancestor = ancestor * 10 + roughLineage.charAt(i) - 48;
    			else
    				ancestor = roughLineage.charAt(i) - 48;
    			k++;
    		}else
    		{
    			lineage[j] = ancestor;
    			j++;
    			k = 0;
    		}
    	}
    	// fix array sizes because they are initialised to silly sizes
    	for(int i = 0; i < lineage.length; i++)
    	{
    		if(lineage[i] == 0)
    		{
    			lineage = Arrays.copyOf(lineage, i);
    			break;
    		}
    	}
    	return lineage;
    }
    
    private String listAncestorsRecursive(Node x, int y, String answer)
    {
    	if(x.left != null && answer == "")
    	{
    		if(x.left.value != y)
    			answer = listAncestorsRecursive(x.left, y, answer);
    		else
    			//have to also print the current value to properly list it
    			return answer + "." + x.left.value + "." + x.value;
    	}
    	if(x.right != null && answer == "")
    	{
    		if(x.right.value != y)
    			answer = listAncestorsRecursive(x.right, y, answer);
    		else
    			return answer + "." + x.right.value + "." + x.value;
    	}
    	if(answer != "")
    		return answer + "." + x.value;
    	else
    		return answer;
    }
    
    // find the lowest common ancestor by comparing lineages
    public int lowestCommonAncestor(int list[])
    {
    	int lineages[][] = new int[list.length][];
    	for(int i = 0; i < list.length; i++)
    	{
    		lineages[i] = listAncestors(list[i]);
    	}
    	// finds the shortest array so we never go beyond its length
    	int shortCount = 0;
    	int shortName = 0;
    	for(int i = 0; i < lineages.length; i++)
    	{
    		if(lineages[i][0] < shortCount || shortCount == 0)
    		{
    			shortName = i;
    			shortCount = lineages[0][i];
    		}
    	}
    	// iterate through, taking each value in our first array and checking to see its presence in the others
    	// since the arrays are in descending value, the lca is whatever we find first
    	int lca = 0;
    	for(int i = 1; i < shortCount; i++)
    	{
    		boolean test1 = true;
    		for(int j = 1; j < lineages.length; j++)
    		{
    			boolean test2 = false;
    			for(int k = 0; k < lineages[j].length; k++)
    			{
    				if(lineages[j][k] == lineages[shortName][i])
    				{
    					test2 = true;
    					break;
    				}
    			}
    			if(!test2)
    			{
    				test1 = false;
    				break;
    			}
    		}
    		if(test1)
    		{
    			lca = lineages[shortName][i];
    			break;
    		}
    	}
    	return lca;
    }
}
