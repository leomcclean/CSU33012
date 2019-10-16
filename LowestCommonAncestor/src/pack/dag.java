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
		adj = new ArrayList<>(size);
	}
	
	// insert a new edge - we also check if this makes the graph cyclic
	public boolean edge(int start, int end)
	{
		// if any of the variables are invalid
		if(start > size || start < 0 || end > size || end < 0)
			return false;
		// copy the list and use its copy to test for a cycle
		ArrayList<ArrayList<Integer>> adj2 = adj;
		adj2.get(start).add(end);
		if(isAcyclic(adj2))
			return false;
		// if the addition does not create a cycle we can add it to the main adjacency list
		adj.get(start).add(end);
		return true;
	}
	
	// test for cycles within a graph to ensure it is a DAG
	public boolean isAcyclic(ArrayList<ArrayList<Integer>> test)
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

	public int lca(int one, int two)
	{
		int[] visited = new int[size];
		int[] familyOne = breadthFirstSearch(visited, one, 1);
		int[] familyTwo = breadthFirstSearch(familyOne, two, 2);
		
		int[] count = new int[size];
		for(int i = 0; i < size; i++)
		{
			if(familyTwo[i] == 2)
			{
				ArrayList<Integer> temp = adj.get(i);
				for(int j = 0; j < temp.size(); j++)
				{
					if(familyTwo[temp.get(j)] == 2)
						count[i]++;
				}
			}
		}
		for(int i = 0; i < count.length; i++)
		{
			if(familyTwo[i] == 2 && count[i] == 0)
				return count[i];
		}
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
