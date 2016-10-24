import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MyKruskalsMST{
	
	//used to link vertices with their group number
	private class VertexGroup
	{
		public int v = 0;
		public int group = 0;
		
		VertexGroup(int v, int g)
		{
			this.v = v;
			this.group = g;
		}
		
		public String toString()
		{
			return "Vertex: " + v + " Group: " + group;
		}
		
	}
	
	public List<MyEdge> findGraph(MyGraph g)
	{
		List<MyEdge> F = new ArrayList<MyEdge>();	//list of mst edges
		List<MyEdge> edges = new ArrayList<MyEdge>();
		int numVertices = g.numVertices();
		int edgeIndex = 0;
		MyEdge min;
		
		//set up the edge
		int i = 0;
		for(MyEdge es :g.getEdges())
		{
			edges.add(es);
		}
			
		
		//array that keeps track of vertices and their groups
		VertexGroup [] V = new VertexGroup[numVertices];

		//set up the vertex groups
		i = 0;
		for(int vert :g.getVertexKeys())
		{
			V[i++] = new VertexGroup(vert,vert);
		}
		
		//sort edges, O(m log m)  http://docs.oracle.com/javase/7/docs/api/java/util/Collections.html#sort(java.util.List)
		Collections.sort(edges);
		
		//while not solved
		numVertices--;	//set numVertices to numNodes in the MST
		while(F.size() < numVertices)
		{
			//select next min edge
			min = edges.get(edgeIndex++);
			
			//if vertices not in same group		
			if(V[min.Vert1].group != V[min.Vert2].group)
			{
				//join groups
				V[min.Vert2].group = V[min.Vert1].group;
				F.add(min);
			}
		}
		
		return F;
	}

}



