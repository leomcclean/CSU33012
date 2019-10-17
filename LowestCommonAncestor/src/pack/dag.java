package pack;
import java.util.ArrayList;

public class dag
{
	private int size;
	private ArrayList<ArrayList<Integer>> adj;
	private ArrayList<ArrayList<Integer>> radj;
	
	// set the adjacency list to the size of the number of nodes
	public dag(int size)
	{
		this.size = size;
		if(size > 0)
		{
			adj = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i < size; i++)
				adj.add(new ArrayList<Integer>());
			radj = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i < size; i++)
				radj.add(new ArrayList<Integer>());
		}
	}
	
	// insert a new edge - we also check if this makes the graph cyclic
	public boolean edge(int start, int end)
	{
		// if any of the variables are invalid
		if(start > size - 1	 || start < 0 || end > size - 1 || end < 0)
			return false;
		// add the edge
		adj.get(start).add(end);
		radj.get(end).add(start);
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
		
		//we derive the lca by finding the closest ancestor
		// the lowest score (0) is an ancestor with no children closer to the two nodes
		for(int i = 0; i < size; i++)
		{
			// therefore any node with a (0) score is the lca
			if(familyTwo[i] == 2)
				return i;
		}
		// if there are no ancestors in common we can return (-1) for a failure
		return -1;
	}
	
	private int[] breadthFirstSearch(int[] visited, int start, int base)
	{
		ArrayList<Integer> queue = new ArrayList<Integer>(); 

		queue.add(start); 
  
		while(queue.size() != 0) 
		{ 
			int current = queue.get(0);
			queue.remove(0);
			
			if(!radj.get(current).isEmpty())
			{
				for(int i = 0; i < radj.get(current).size(); i++) 
				{ 
					int j = radj.get(current).get(i);
					visited[j]++; 
					queue.add(j);
				}
			}
		}
		return visited;
	}
	
}
