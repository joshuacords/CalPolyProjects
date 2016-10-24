//Undirected, non-circular, non-negative vertices

public class MyEdge implements Comparable<Object> {
	int vert1;
	int vert2;
	public double weight;
	boolean marked;
	MyEdge()
	{
		
	}
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
			vert1 = v1;
			vert2 = v2;
		}
		else
		{
			vert1 = v2;
			vert2 = v1;			
		}
		this.weight = weight;
		marked = false;
	}
	
	public void setEdge(int v1, int v2, double weight)
	{
		//for non-circular, non-negative vertices
				if((v1 < 0 ) || (v2 < 0 ) || (v1 == v2))
				{
					System.out.println("Circular Edge created or negative vertex assignment.");
					System.exit(0); 
				}
				if(v1 < v2)
				{
					vert1 = v1;
					vert2 = v2;
				}
				else
				{
					vert1 = v2;
					vert2 = v1;			
				}
				this.weight = weight;
				marked = false;
	}
	
	public boolean equals(Object other)	//will not check weights, doesn't care about direction
	{
		//System.out.println("Edge = other.vert1 " + ((MyEdge)other).vert1 + " vert1 " + vert1 + "other.vert2 " + ((MyEdge)other).vert2 + " vert2 " + vert2);
		   
	    if (other == null)
	    {
	       return false;
  	    }
	
	    if (this.getClass() != other.getClass())
	    {
	       return false;
	    }
	    
	    
	    if( ((MyEdge)other).vert1 == vert1 && ((MyEdge)other).vert2 == vert2) return true;
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
		if(v1 == vert1)	return vert2;
		else if(v1 == vert2) return vert1;
		else return -1;
	}
	
	boolean connectedTo(int v1)
	{
		if(v1 == vert1 || v1 == vert2) return true;
		return false;
	}
	
	public String toString()
	{
		String s = new String();
		s = "E(" + vert1 + ", " + vert2 + " : " + weight +")";
		return s;
		
	}
	
	public int compareTo(Object otherEdge) throws ClassCastException {
	    if (!(otherEdge instanceof MyEdge))
	      throw new ClassCastException("A MyEdge object expected.");
	    double otherWeight = ((MyEdge) otherEdge).weight;
	    return (int)(this.weight - otherWeight);    
	  }
}
