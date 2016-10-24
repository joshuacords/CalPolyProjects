import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;


public class MyGraphConstructor {
	MyGraphConstructor()
	{}
	
	void ConstructGraph(MyGraph g, String fileName)
	{
		//file format for edge list
		//numVertices numEdges String("directed" or "undirected")
		//e1.vert1 e1.vert2
		//e2.vert1 e2.vert2 ...
		g.reset();
		File f = new File(fileName);
		try{
			String graphType = new String();
			Scanner s = new Scanner(f);
			int vertices = s.nextInt();
			int edges = s.nextInt();
			graphType += s.nextLine();
			while(s.hasNextInt())
			{
				g.addEdge(s.nextInt(), s.nextInt(), s.nextDouble());
			}
			s.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println(e);
			return;
		}
	}
	
	void ConstructGraph(MyGraph g, List<MyEdge> E)
	{
		g.reset();
		for(MyEdge e : E)
		{
			g.addEdge(e);
		}
	}
	
	void ConstructGraph(MyGraph g, double [][] array, int size)
	{
		g.reset();
		for(int i = 0; i < size; i++)
		{
			for(int j = i + 1; j < size; j++)
			{
				if(array[i][j] > 0)
				{
					g.addEdge(new MyEdge(i,j,array[i][j]));
				}
			}
		}
		
		
	}
	

}
