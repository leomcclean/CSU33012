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
	public boolean isAcyclic(ArrayList test)
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
}
