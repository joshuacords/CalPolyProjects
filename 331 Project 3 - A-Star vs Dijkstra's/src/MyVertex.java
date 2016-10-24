import java.util.*;

public class MyVertex {
	int number;
	boolean marked;
	List<MyEdge> edges;
	
	public MyVertex(int number)
	{
		this.number = number;
		marked = false;
		edges = new ArrayList<MyEdge>();
	}
	
	public boolean addEdge(MyEdge e)
	{
		for(MyEdge edge : edges)
		{
			if(edge == e) return false;
		}
		edges.add(e);
		return true;
	}
	
	public void mark()
	{
		marked = true;
	}
	
	public void unMark()
	{
		marked = false;
	}
	
	public void toggleMark()
	{
		if(marked) marked = false;
		else marked = true;
	}
	
	public boolean equals(Object other)	//will not check weights, doesn't care about direction
	{
		if (other == null)
	    {
	       return false;
  	    }
	
	    if (this.getClass() != other.getClass())
	    {
	       return false;
	    }
	    
		if(((MyVertex)other).number == number) return true;
		return false;
	}
	
	public String toString()
	{
		String s = new String();
		s = "V(" + number + ") ";
		for(MyEdge e : edges)
		{
			s += e + " ";
		}
		return s;
		
	}

	public boolean remove(MyEdge e) {
		System.out.println("In Vertex " + toString() + "\nRemoving: " + e);
		return edges.remove(e);
	}
}
