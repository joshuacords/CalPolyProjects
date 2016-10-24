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
					//array[i][j] = array[j][i] = Math.random()*maxWeight;
					array[i][j] = array[j][i] = (int)(Math.random()*10);	//for eye test
					edges--;
				}else
				{
					array[i][j] = array[j][i] = -1;
				}
			}
		}
		return array;
	}
	
	double[][] toMatrixArrayGrid(int nodes, int cuts)	//side = number of nodes on each side
	{
		int side = (int)Math.pow(nodes, .5);
		cuts/=2;
		int orgCuts = cuts;
		
		double[][] array = new double[nodes][nodes];
		
		for(int i = 0; i < nodes; i++)
		{
			for(int j = 0; j < nodes; j++)
			{
				array[i][j] = -1;
			}
		}
		
//		for(int i = 0; i < nodes - side; i++)
//		{
//			if(cuts > 0 && Math.random() < .3)	//dead ends random nodes until enough cuts are made
//			{
//				cuts--;
//			}else{
//				array[i][i + 1] = array[i + 1][i] = (int)(Math.random()*2)+2;	//links each node with one on right
//				array[i][i + side] = array[i + side][i] = (int)(Math.random()*2)+2;	//links each node with one below
//				if((i+1)%side == 0)array[i][i + 1] = array[i + 1][i] = -1;	//removes links to the right of the end nodes
//			}
//		}
		
		for(int i = 0; i < nodes - side; i++)
		{
			array[i][i + 1] = array[i + 1][i] = (int)(Math.random()*2)+2;	//links each node with one on right
			array[i][i + side] = array[i + side][i] = (int)(Math.random()*2)+2;	//links each node with one below
			if((i+1)%side == 0)array[i][i + 1] = array[i + 1][i] = -1;	//removes links to the right of the end nodes
		}
		
		//connects last row of horizontal nodes
		for(int i = nodes - side; i < nodes-1; i++)
		{
			array[i][i + 1] = array[i + 1][i] = (int)(Math.random()*2)+2;
		}
		
		//
		MyGraphConstructor gc = new MyGraphConstructor();
		MyGraph g = new MyGraph();
		gc.ConstructGraph(g, array, nodes);
		
		List<MyEdge> edges = g.getEdges();
		List<MyEdge> deleteEdges = new ArrayList<MyEdge>();
		int index = 0;
		
//		System.out.println("Before Edges: " + edges);
		
		//select edges to cut
		while(cuts>0)
		{
			index = (int)(Math.random()*edges.size());
			deleteEdges.add(edges.get(index));
			edges.remove(index);
			cuts--;
		}
//		System.out.println("After  Edges: " + edges);
		
		
		
		//delete edges
		for(MyEdge e: deleteEdges)
		{
			array[e.vert1][e.vert2] = -1;
		}
		
//		while(cuts > 0)
//		{
//			int rand1, rand2;
//			rand1 = (int)Math.random()*nodes;
//			rand2 = (int)Math.random()*nodes;
//			while(array[rand1][rand2] < 0)
//			{
//				rand1++;
//				rand2++;
//				rand1%=nodes;
//				rand2%=nodes;
//			}
//			array[rand1][rand2] = -1;
//			cuts--;
//		}
//		
//		//connects first row of vertical nodes to ensure a path but at a worst possible value
//		for(int i = 0; i < nodes-side; i+=side)
//		{
//			array[i][i + side] = array[i + side][i] = (int)(Math.random()*2)+4;	//links each node with one below
//		}
		
//		System.out.println("Array to Grid:");
//		for(int j = -1; j < nodes; j++)
//		{
//			System.out.printf("%3d ",j);
//		}
//		for(int i = 0; i < nodes; i++)
//		{
//			System.out.printf("\n%3d ",i);
//			for(int j = 0; j < nodes; j++)
//			{
//				System.out.printf("%3d ",(int)array[i][j]);
//			}
//			
//		}
				
		return array;
	}
	
	double[][] toMatrixArrayTest(int nodes, int edges)
	{
		double[][] array = new double[nodes][nodes];
		
		for(int i = 0; i < nodes; i++)
		{
			for(int j = 0; j < nodes; j++)
			{
				array[i][j] = -1;
			}
		}
//		
//		array[1][0] = array[0][1] = 2;
//		array[2][0] = array[0][2] = 5;
//		array[3][0] = array[0][3] = 3;
//		array[2][1] = array[1][2] = 8;
//		array[3][1] = array[1][3] = 6;
//		array[3][2] = array[2][3] = 4;
//		
		
		for(int i = 0; i < nodes; i++)
		{
			for(int j = i + 1; j < nodes; j++)
			{
				if(edges-- > 0)
				{
					System.out.print(edges + " ");
					array[i][j] = array[j][i] = (int)(Math.random()*10);	//for eye test
				}
			}
		}
		
//		System.out.println("Array to Grid:");
//		for(int j = -1; j < nodes; j++)
//		{
//			System.out.printf("%3d ",j);
//		}
//		for(int i = 0; i < nodes; i++)
//		{
//			System.out.printf("\n%3d ",i);
//			for(int j = 0; j < nodes; j++)
//			{
//				System.out.printf("%3d ",(int)array[i][j]);
//			}
//			
//		}
		
		return array;
	}
}
