import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class EdgeGenerator {

	EdgeGenerator(){}
	
	void toText(int size)
	{
		int maxWeight = (int) ((Math.pow(2, 63)-4)/(size-1));
		int edges = size*(size -1)/2;
		FileWriter fWriter;
		PrintWriter outputFile;
		String fileName = "Text_Files/graph" + size + ".txt";
		try {
			fWriter = new FileWriter(fileName, false);
			outputFile = new PrintWriter(fWriter);
			outputFile.write(size + " " + edges + " undirected" + System.lineSeparator());
			
			for(int i = 0; i < size; i++)
			{
				for(int j = i+1; j < size; j++)
				{
					//calculates
					outputFile.append(i + " " + j + " " + Math.random()*maxWeight + System.lineSeparator());
				}
			}
			outputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	List<MyEdge> toList(int nodes, int edges)
	{
		int maxWeight = (int) ((Math.pow(2, 63)-4)/(nodes-1));
		List<MyEdge> le = new ArrayList<MyEdge>();
	
		int k = 0;
		for(int i = 0; i < nodes; i++)
		{
			for(int j = i+1; j < nodes; j++)
			{
				//calculates
				le.add(new MyEdge(i,j,Math.random()*maxWeight));
				if(k++ > edges) return le;
			}
		}
		return le;
	}
	
	double[][] toMatrixArray(int nodes, int edges)
	{
		int maxWeight = (int) ((Math.pow(2, 63)-4)/(nodes-1));
		double[][] array = new double[nodes][nodes];
		
		for(int i = 0; i < nodes; i++)
		{
			array[i][i] = -1;
		}
		
		for(int i = 0; i < nodes; i++)
		{
			for(int j = i + 1; j < nodes; j++)
			{
				if(edges > 0)
				{
					array[i][j] = array[j][i] = Math.random()*maxWeight;
					edges--;
				}else
				{
					array[i][j] = array[j][i] = -1;
				}
			}
		}
		return array;
	}
}
