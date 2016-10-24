import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyGraph {
	private int numV;
	private int numE;
	private HashMap<Integer, MyVertex> V;
	private HashMap<String, MyEdge> E;
	
	private class EdgeIndex
	{
		int v1;
		int v2;
		
		EdgeIndex(int v1, int v2)
		{
			this.v1 = v1;
			this.v2 = v2;
		}
	}
	
	MyGraph()
	{
		V = new HashMap<Integer, MyVertex>();
		E = new HashMap<String, MyEdge>();
		
		numV = 0;
		numE = 0;
	}
	
	boolean addVertex(int v)
	{
		return addVertex(new MyVertex(v));
	}
	
	boolean addVertex(MyVertex v)
	{		
		if(V.containsKey(v.number)) return false;
		numV++;
		V.put(v.number, v);
		return true;
	}
	
	boolean addEdge(int v1, int v2, double weight)
	{
		return addEdge(new MyEdge(v1, v2, weight));
	}
	
	boolean addEdge(MyEdge e)
	{
		String ei = Integer.toString(e.vert1) + ", " + Integer.toString( e.vert2);
		
		//check if already in HaspMap
		if(E.containsKey(ei)) return false;
		
		//check to see if vertex 1 exists, if not add it
		if(!V.containsKey(e.vert1)) addVertex(e.vert1);
		//add edges to vertex
		(V.get(e.vert1)).addEdge(e);
		
		//check to see if vertex 2 exists, if not add it
		if(!V.containsKey(e.vert2)) addVertex(e.vert2);
		//add edges to vertex
		(V.get(e.vert2)).addEdge(e);
		
		E.put(ei, e);
		numE++;
		return true;
	}
	
	int numVertices()
	{
		return numV;
	}
	
	int numEdges()
	{
		return numE;
	}
	
	MyVertex getFirstVertex()
	{
		Map.Entry<Integer, MyVertex> entry = V.entrySet().iterator().next();
		return entry.getValue();
	}
	
	MyVertex getVertex(int v)
	{
		return V.get(v);
	}
	
	MyEdge getEdge(int v1, int v2)
	{
		return E.get(new EdgeIndex(v1, v2));
	}
	
	int[] getVertexKeys()
	{
		int[] keys = new int[numV];
		int i = 0;
		for(int v : V.keySet())
		{
			keys[i++] = v;
		}
		return keys;
	}
	
	MyVertex[] getVertices()
	{
		MyVertex[] vertices = new MyVertex[numV];
		int i = 0;
		for(MyVertex v : V.values())
		{
			vertices[i++] = v;
		}
		return vertices;
	}
	
//	MyEdge[] getEdges()
//	{
//		MyEdge[] edges = new MyEdge[numE];
//		int i = 0;
//		for(MyEdge e : E.values())
//		{
//			edges[i++] = e;
//		}
//		return edges;
//	}
	
	List<MyEdge> getEdges()
	{
		List<MyEdge> edges = new ArrayList<MyEdge>();
		edges.addAll(E.values());
		return edges;
	}
	
	void remove(MyVertex v)
	{
		if(V.remove(v.number) != null) numV--;
	}
	
	void remove(MyEdge e)
	{
		String ei = Integer.toString(e.vert1) + ", " + Integer.toString( e.vert2);
		
		//remove e from hashmap
		if(E.remove(ei) != null) numE--;
	}
	
	void reset()
	{
		V = new HashMap<Integer, MyVertex>();
		E = new HashMap<String, MyEdge>();
		
		numV = 0;
		numE = 0;
	}
	
	public String toString()
	{
		String s = new String();
		s += "Vertices (" + numV + "):\n";
		for (MyVertex v : V.values()) 
		{
			s += v.toString();
			s += '\n';
		}
		
		s+= "\nEdges (" + numE + "):\n";
		for (MyEdge v : E.values()) 
		{
			s += v.toString();
			s += '\n';
		}
		return s;
		
	}
	
	public void printVertex(int v)
	{
		System.out.println(V.get(v));
	}
}
