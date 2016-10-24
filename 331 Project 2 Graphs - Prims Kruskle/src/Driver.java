import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class Driver {
	static MyTimer timer;
	static String fileName = "src/Graph_Results.txt";
	static FileWriter fWriter;
	static PrintWriter outputFile;
	static int arraySize;
	
	public static void main(String[] args) {
		
		MyGraph g = new MyGraph();
		List<MyEdge> le;
		int graphSize = 0;
		timer = new MyTimer();
		String graphType = "test type";
		String caseType = "";
		int graphPower = 0;
		int numEdges = 0;
				
		MyGraphConstructor gc = new MyGraphConstructor();
		Scanner scanner = new Scanner(System.in);
		String choice, cases;
		if(args.length>1)
		{
			choice = args[0];
			graphPower = Integer.parseInt(args[1]);
			cases = args[2];
		}
		else
		{
			System.out.println("What graph algorithm? (p,d,f,k):");
			choice = scanner.next();
			
			System.out.println("Graph size 2^x: What is x?");
			graphPower = scanner.nextInt();
			
			System.out.println("Select Case: b,w,c  (c is for Prim's vs Dijkster's comparison)");
			cases = scanner.next();
		}
		
		switch(choice)
		{
			case "p": 
				graphType = "Prim's MST";
				break;
			case "d": 
				graphType = "Dijkster's";
				break;
			case "f":
				graphType = "Floyd's";
				break;
			case "k": 
				graphType = "Kruskal's";
				break;
			
			default:	System.out.println("Invalid: Input 'p' for Prim's, 'd' for Dijkster's, 'f' for Floyd's , 'k' for Kruskal's. Example: h 4");
				scanner.close();
				return;
		}
		scanner.close();
		switch(cases)
		{
			case "b":  caseType = "Best Case";
				break;
			case "w":  caseType = "Worst Case";
				break;
			case "c":  caseType = "Prim's vs Kruskal's Comparison";
			default:;
			
		}
		System.out.println(graphType + " " + caseType);
		System.out.printf("%2s%7s%13s\n","2^","N","Microseconds");
		
		try {
			fWriter = new FileWriter(fileName, true);
			outputFile = new PrintWriter(fWriter);
			outputFile.append(graphType + " " + caseType + "\n");
			outputFile.append("2^," + "N," + "Microseconds, , , , , , , , , , " + graphType + "\n");
			
			//for increasing graph sizes
			for(int j = 3; j <= graphPower; j++)
			{
				int numRuns = 10;
				double array [][];
				long average[] = new long[2];	//stores averages as time, comparisons, moves
				outputFile.append(j + ", " + (int)Math.pow(2, j) + ", ");
				for(int k = 0; k < numRuns; k++)	//averages 10 runs
				{
					graphSize = (int) Math.pow(2, j);
					EdgeGenerator eg = new EdgeGenerator();
					
					switch(cases)
					{
						case "b":  numEdges = graphSize -1;
							break;
						case "w":  numEdges = (graphSize -1)*(graphSize)/2;
							break;
						case "c":  compare(graphPower);
						return;
						default:   numEdges = graphSize -1;
						
					}
					
					switch(choice)
					{
						case "p": NewPrimsMST p = new NewPrimsMST();
							array = eg.toMatrixArray(graphSize, numEdges);
							timer.reset();
							le = p.findGraph(graphSize, array);							
							break;
						case "d": Dikstras d = new Dikstras();
							array = eg.toMatrixArray(graphSize, numEdges);
							timer.reset();
							le = d.findGraph(graphSize, array);
							break;
						case "f": Floyds f = new Floyds();
							double[][] shortestPaths = new double[graphSize][graphSize];
							int[][] pathIndexes = new int[graphSize][graphSize];
							array = eg.toMatrixArray(graphSize, numEdges);
							timer.reset();
							f.findGraph(graphSize, array, shortestPaths, pathIndexes);
							break;
						case "k": MyKruskalsMST kruskals = new MyKruskalsMST();
							le = eg.toList(graphSize, numEdges);
							gc.ConstructGraph(g, le);
							timer.reset();
							le = kruskals.findGraph(g);
							break;
						default:	
							return;
					}
					
					
					average[0] = timer.getElapsedTime();
					average[1] += average[0];
					
					outputFile.append(average[0] + ", ");
					g.reset();
										
				}
				
				average[1] /= numRuns;
				outputFile.append(average[1] + System.getProperty("line.separator"));
								
				System.out.printf("%2d%7d%13d\n",j,(int)Math.pow(2, j),average[0]/1000);
			}
			outputFile.append("\n\n");
			outputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	
	static void compare(int graphPower)
	{
		double[][] array;
		MyGraphConstructor gc = new MyGraphConstructor();
		MyGraph g = new MyGraph();
		EdgeGenerator eg = new EdgeGenerator();
		List<MyEdge> le = new ArrayList<MyEdge>();
		//start at midpoint
		int graphSize = (int)Math.pow(2, graphPower);
		int numEdges = graphSize * (graphSize - 1) / 4;
		int halfAgain = graphSize * (graphSize - 1) / 8;
		NewPrimsMST p = new NewPrimsMST();
		MyKruskalsMST k = new MyKruskalsMST();
		
		long Prims = 0, Kruskals = 0;
		
		//find closest comparison after 50 tries
		for(int i = 0; i < 20; i++)
		{
			//average of 5 times
			for(int j = 0; j < 5; j++)
			{
				array = eg.toMatrixArray(graphSize, numEdges);
				timer.reset();
				le = p.findGraph(graphSize, array);							
				Prims += timer.getElapsedTime();
				
				le = eg.toList(graphSize, numEdges);
				gc.ConstructGraph(g, le);
				timer.reset();
				le = k.findGraph(g);
				Kruskals += timer.getElapsedTime();
				
			}

			System.out.println("Nodes: " + graphSize + " Edges: " + numEdges + " Ratio: " + (numEdges/graphSize));
			//binary closer to mid
			if(Prims > Kruskals)
			{
				numEdges += halfAgain;
				System.out.println("Prims > Kruskals");
				
			} else 
			{
				numEdges -= halfAgain;
				System.out.println("Prims < Kruskals");
			}

			System.out.println(Prims + "  " + Kruskals);
			halfAgain = halfAgain/2;
			Prims = Kruskals = 0;
		}
		
		outputFile.append("Nodes, Edges,  Ratio, \n");
		outputFile.append(graphSize + ", " + numEdges + ", " + (numEdges/graphSize) + ",\n");
		outputFile.close();
	}

}
