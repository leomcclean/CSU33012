package pack;
import java.util.ArrayList;

public class dag
{
	private int size;
	private ArrayList<ArrayList<Integer>> adj;
	
	// set the adjacency list to the size of the number of nodes
	public dag(int size)
	{
		this.size = size;
		if(size > 0)
		{
			adj = new ArrayList<ArrayList<Integer>>(size);
			for(int i = 0; i < size; i++)
				adj.add(new ArrayList<Integer>());
		}
	}
	
	// insert a new edge - we also check if this makes the graph cyclic
	public boolean edge(int start, int end)
	{
		// if any of the variables are invalid
		if(start > size || start < 0 || end > size || end < 0)
			return false;
		// add the edge
		adj.get(start).add(end);
		if(isAcyclic(adj))
		{
			// if the addition creates a cycle we remove the new edge and return an error
			adj.get(start).remove(adj.get(start).size() - 1);
			return false;
		}else
		{
			// if the addition does not create a cycle we can add it to the main adjacency list
			return true;
		}
	}
	
	// test for cycles within a graph to ensure it is a DAG
	private boolean isAcyclic(ArrayList<ArrayList<Integer>> test)
	{
		// a graph with one node or zero nodes is acyclic
		if(test.size() == 1 || test.isEmpty())
			return true;
		// a graph must have one leaf to be acyclic
		// if we peel off all leaves and are left with nothing, the graph is acyclic
		ArrayList<ArrayList<Integer>> adj2 = test;
		while(adj2.isEmpty() == false)
		{
			int j = -1;
			for(int i = 0; i < adj2.size(); i++)
			{
				if(adj2.get(i).isEmpty())
				{
					j = i;
					break;
				}
			}
			if(j == -1)
				return false;
			else
				adj2.remove(j);
		}
		return true;
	}

	// find a lowest common ancestor
	public int lca(int one, int two)
	{
		if(size < 1)
			return -1;
		// use BFS to map ancestors of two nodes
		int[] visited = new int[size];
		// the intersection of the first and second family are candidates for lca
		int[] familyOne = breadthFirstSearch(visited, one, 1);
		int[] familyTwo = breadthFirstSearch(familyOne, two, 2);
		
		// derive the lca by finding the closest ancestor
		int[] count = new int[size];
		for(int i = 0; i < size; i++)
		{
			// if a node is an ancestor of the pair...
			if(familyTwo[i] == 2)
			{
				// ...see if it has a parent that is also an ancestor...
				ArrayList<Integer> temp = adj.get(i);
				for(int j = 0; j < temp.size(); j++)
				{
					// ...if it does, increment the parents score
					if(familyTwo[temp.get(j)] == 2)
						count[j]++;
				}
			}
		}
		// the lowest score (0) is an ancestor with no children closer to the two nodes
		for(int i = 0; i < count.length; i++)
		{
			// therefore any node with a (0) score is the lca
			if(familyTwo[i] == 2 && count[i] == 0)
				return count[i];
		}
		// if there are no ancestors in common we can return (-1) for a failure
		return -1;
	}
	
	private int[] breadthFirstSearch(int[] visited, int start, int base)
	{
		ArrayList<Integer> queue = new ArrayList<Integer>(); 

		visited[start] = 1; 
		queue.add(start); 
  
		while(queue.size() != 0) 
		{ 
			start = queue.get(0);
			queue.remove(0);
  
			for(int i = 0; i < adj.get(start).size(); i++) 
			{ 
				int j = adj.get(start).get(i);
				if (visited[j] == (base - 1)) 
				{ 
					visited[j] = base; 
					queue.add(j);
				}
			}
		}
		return visited;
	}
	
}
