//Undirected, non-circular, non-negative vertices

public class MyEdge implements Comparable<Object> {
	int Vert1;
	int Vert2;
	public double weight;
	boolean marked;
	
	MyEdge(int v1, int v2, double weight)
	{
		//for non-circular, non-negative vertices
		if((v1 < 0 ) || (v2 < 0 ) || (v1 == v2))
		{
			System.out.println("Circular Edge created or negative vertex assignment.");
			System.exit(0); 
		}
		if(v1 < v2)
		{
			Vert1 = v1;
			Vert2 = v2;
		}
		else
		{
			Vert1 = v2;
			Vert2 = v1;			
		}
		
		this.weight = weight;
		marked = false;
	}
	
	public boolean equals(Object other)	//will not check weights, doesn't care about direction
	{
		//System.out.println("Edge = other.Vert1 " + ((MyEdge)other).Vert1 + " Vert1 " + Vert1 + "other.Vert2 " + ((MyEdge)other).Vert2 + " Vert2 " + Vert2);
		   
	    if (other == null)
	    {
	       return false;
  	    }
	
	    if (this.getClass() != other.getClass())
	    {
	       return false;
	    }
	    
	    
	    if( ((MyEdge)other).Vert1 == Vert1 && ((MyEdge)other).Vert2 == Vert2) return true;
		return false;
	}
	
	void mark()
	{
		marked = true;
	}
	
	void unMark()
	{
		marked = false;
	}
	
	void toggleMark()
	{
		if(marked) marked = false;
		else marked = true;
	}
	
	int otherVert(int v1)
	{
		if(v1 == Vert1)	return Vert2;
		else if(v1 == Vert2) return Vert1;
		else return -1;
	}
	
	boolean connectedTo(int v1)
	{
		if(v1 == Vert1 || v1 == Vert2) return true;
		return false;
	}
	
	public String toString()
	{
		String s = new String();
		s = "E(" + Vert1 + ", " + Vert2 + " : " + weight +")";
		return s;
		
	}
	
	public int compareTo(Object otherEdge) throws ClassCastException {
	    if (!(otherEdge instanceof MyEdge))
	      throw new ClassCastException("A MyEdge object expected.");
	    double otherWeight = ((MyEdge) otherEdge).weight;
	    return (int)(this.weight - otherWeight);    
	  }
}
