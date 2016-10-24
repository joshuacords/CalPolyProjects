//psuedocode from "Foundations of Algorithms" by Richard Neapolitan and Kumarss Naimipour
//copyright 1996 by D. C. Health and Company

public class Floyds {
	public void findGraph(int n, double[][] array, double[][] shortestPaths, int [][] pathIndexes) {
		
		int i,j,k;
		for(i=0; i < n; i++)
		{
			for(j=0; j < n; j++)
			{
				pathIndexes[i][j] = 0;	//initialize path indexes array to no extra stops
			}
		}
		
		//iterate through each possible 3 node connection on the array in a very careful way
		for(k=0; k < n; k++)
		{
			for(i = 0; i < n; i++)
			{
				for(j = 0; j < n; j++)
				{
					//if adding vertex k to path i -> j is shorter
					if(array[i][k] + array[k][j] < array[i][j])
					{
						//add k to the path indexes and change the weight to get from i to j accordingly
						pathIndexes[i][j] = k;
						array[i][j] = array[i][k] + array[k][j];
					}
				}
			}
		}
		
		return;
	}
}
