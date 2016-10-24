import java.util.ArrayList;
import java.util.List;

//psuedocode from "Foundations of Algorithms" by Richard Neapolitan and Kumarss Naimipour
//copyright 1996 by D. C. Health and Company

public class Dikstras{

	public List<MyEdge> findGraph(int n, double[][] array) {
		
		int i, near = 0;
		double min;
		MyEdge e;
		int [] touch = new int[n];
		double [] distance = new double[n];
		
		List<MyEdge> F = new ArrayList<MyEdge>();	//all edges in MST
		
		//initialize a nearest array listing nearest vertices
		//initialize a co-array with accompanying weights
		for(i = 1; i < n; i++)
		{
			touch[i] = 0;
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
			e = new MyEdge(near, touch[near],distance[near]);
			F.add(e);
			
			//update nearest and distance lists to a new set of vertices taking into account distance through the entire path
			for(i = 1; i < n; i++)
			{
				//if a element is closer when the distance is added, replace the distance and it's index in nearest
				if(array[i][near] >= 0 && distance[near] + array[i][near] < distance[i])
				{
					distance[i] = distance[near] + array[i][near];
					touch[i] = near;
				}
			}
			distance[near] = -1;
			
		}
		
		
		return F;
	}
	
	

}

