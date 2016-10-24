import java.util.ArrayList;
import java.util.List;

//psuedocode from "Foundations of Algorithms" by Richard Neapolitan and Kumarss Naimipour
//copyright 1996 by D. C. Health and Company

public class NewPrimsMST{

	public List<MyEdge> findGraph(int n, double[][] array) {
		
		int i, near = 0;
		double min;
		MyEdge e;
		int [] nearest = new int[n];
		double [] distance = new double[n];
		
		List<MyEdge> F = new ArrayList<MyEdge>();	//all edges in MST
		
		//initialize a nearest array listing nearest vertices
		//initialize a co-array with accompanying weights
		for(i = 1; i < n; i++)
		{
			nearest[i] = 0;
			distance[i] = array[0][i];
		}
		
		//for n-1 times
		for(int j = 1; j < n; j++)
		{
			//find min weighted edge
			min = Double.POSITIVE_INFINITY;
			for(i = 1; i < n; i++)
			{
				if(0 <= distance[i] && distance[i] < min)
				{
					min = distance[i];
					near = i;
				}
			}
			
			//add edge to F and "turn off" the weight
			e = new MyEdge(near, nearest[near],distance[near]);
			F.add(e);
			distance[near] = -1;
			
			//update nearest and distance lists to a new set of vertices
			for(i = 1; i < n; i++)
			{
				//if a element is closer replace the distance and it's index in nearest
				if(array[i][near] >= 0 && array[i][near] < distance[i])
				{
					distance[i] = array[i][near];
					nearest[i] = near;
				}
			}
			
		}
		
		
		return F;
	}
	
	

}
