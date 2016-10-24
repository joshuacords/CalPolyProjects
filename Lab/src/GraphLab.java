import java.io.IOException;
import graph.*;


public class GraphLab {
	public static void main(String []args) throws IOException
	{
		DfsGraph graph = new DfsGraph("Text_Files/graph5.txt");
		System.out.print(graph);
		int components = presistent(graph);
		//System.out.print("Called " + components + " times");
		if(components + graph.getSize() > graph.getOrder()) System.out.println("The graph is cyclic!");
		else System.out.println("The graph is acyclic");
	}
	
	public static int presistent(DfsGraph graph)
	{
		int numVertex = graph.getOrder(), called = 0;
		for(int i = 0; i < numVertex; i++)
		{
			if(!graph.getVertex(i).isMarked())
			{
				graph.dfs(i);
				called++;
			}
		}
		return called;
	}
}
