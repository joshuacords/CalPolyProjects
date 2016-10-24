import java.util.ArrayList;
import java.util.List;

//psuedocode from "Foundations of Algorithms" by Richard Neapolitan and Kumarss Naimipour
//copyright 1996 by D. C. Health and Company

public class Dijkstras{

	public List<MyEdge> findGraph(int n, int p, int q, double[][] array) {	//n is number of nodes, p is starting node, q is target node
		
		int i,j, near = p, parent = -2; //near is initialized to the starting node
		double min;
		MyEdge e;
		int [] touch = new int[n];
		double [] distance = new double[n];
		int [] previous = new int[n];	//will keep track of each node's parents
		
		//initializing all previous array's paths
		for(i = 0; i < n; i++)
		{
			previous[i] = -1; //initialize all previous nodes to -1
		}
		
		//set starting node to -2 to know when to stop when backtracking
		//previous[p] = -2;
		
		List<MyEdge> F = new ArrayList<MyEdge>();	//all edges in MST
		
		//initialize a nearest array listing nearest vertices
		//initialize a co-array with accompanying weights
		//initializing from vertex p
		for(i = 0; i < n; i++)
		{
			touch[i] = p;				//touch is all set to p
			distance[i] = array[p][i];	//initialize all distances from node p
		}
		
		
		//for n-1 times
		for(j = 1; j < n; j++)
		{
			//find min weighted edge and set that node to be next nearest
			min = Double.POSITIVE_INFINITY;
			for(i = 0; i < n; i++)
			{
				if(0 <= distance[i] && distance[i] < min)
				{
					min = distance[i];
					near = i;
				}
			}
			
					
			//add edge to F and "turn off" the weight
			e = new MyEdge(near, touch[near], distance[near]);
			F.add(e);
			
			
			if(previous[near] < 0)	//if first time visiting node set it's parent
			{
				previous[near] = touch[near];
			}
			
			//update nearest and distance lists to a new set of vertices taking into account distance through the entire path
			for(i = 0; i < n; i++)
			{
				//check for -1 values
				if(array[i][near] >= 0)
				{
					//if they were not previously connected or if the connection would be shorter
					if(-1 == distance[i] || distance[near] + array[i][near] < distance[i])
					{
						//replace the distance and it's index in nearest
						distance[i] = distance[near] + array[i][near];
						touch[i] = near;
					}
				}
			}
			//remove connections to last node and new connected node
			distance[near] = -2;
			distance[touch[near]] = -2;
			
			if(near == q)
			{
				return returnPath(q, p, previous, array);	//if we arrived at our target node return the list
			}
		}
		
		return null;
	}
	
	
	public List<MyEdge> returnPath(int n, int o, int[] array, double[][] weights)
	{
		//from target node n working backward to starting node o
		int i = n;
		List<MyEdge> l = new ArrayList<MyEdge>();
		
		do{
			l.add(new MyEdge(i,array[i],weights[i][array[i]]));
			i = array[i];
		}while(i != o);
		
		return l;
	}
	

}

