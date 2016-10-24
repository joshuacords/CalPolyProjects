import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class Driver {
	static MyTimer timer;
	static String fileName = "src/A-Star Results.txt";
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
		int graphPower = 2;
		int startPoint = 0;
		int endPoint = 0;
		Scanner scanner = new Scanner(System.in);
		String cases = "b";
		
		System.out.println("Graph size x^2: What is x?");
		graphPower = scanner.nextInt();
		
		System.out.println("Select Case: b,w,c  (c is for Prim's vs Dijkster's comparison)");
		cases = scanner.next();
		
		
		
		scanner.close();
		switch(cases)
		{
			case "b":  caseType = "Center to Corner";
				break;
			case "w":  caseType = "Corner to Corner";
				break;
			case "c":  caseType = "Prim's vs Kruskal's Comparison";
			default:;
			
		}
		System.out.println("Dijkster's vs A-Star " + caseType);
		System.out.printf("%2s%7s%13s%13s\n","j","j^2","Microseconds","Microseconds");
		
		try {
			fWriter = new FileWriter(fileName, true);
			outputFile = new PrintWriter(fWriter);
			outputFile.append("Dijkster's vs A-Star " + caseType + System.getProperty("line.separator"));
			outputFile.append(",j,j^2," + "Microseconds, , , , , , , , , , Average" + System.getProperty("line.separator"));
			StringBuilder D = new StringBuilder();
			StringBuilder A = new StringBuilder();
			//for increasing graph sizes
			for(int j = 10; j <= graphPower; j++)
			{
				int numRuns = 10;
				double array [][];
				long averageA[] = new long[2];	//stores averages as time
				long averageD[] = new long[2];	//stores averages as time
				
				D.append("Dijkstra's, " + j + ", " + (int)Math.pow(j, 2) + ", ");
				A.append("A-Star    , " + j + ", " + (int)Math.pow(j, 2) + ", ");
				for(int k = 0; k < numRuns; k++)	//averages 10 runs
				{
					graphSize = (int) Math.pow(j, 2);
					EdgeGenerator eg = new EdgeGenerator();
					int side = (int)Math.pow(graphSize, .5);
					int centerPoint = side/2 + side*(side/2);
					switch(cases)
					{
						case "b":  startPoint = centerPoint;
							break;
						case "w":  startPoint = graphSize-1;
							break;
						case "c":  compare(graphPower);
						return;
						default:   startPoint = centerPoint;
						
					}
					
					Dijkstras d = new Dijkstras();
					AStar a = new AStar();
					
					//toMatrixArrayGrid(graphSize, number of connections to cut /2)
					array = eg.toMatrixArrayGrid(graphSize, graphSize/2);
					timer.reset();
					le = d.findGraph(graphSize, startPoint, endPoint , array);
					
					//if no path don't take
					if(le == null)
					{
						k--;
					}else{
						
						averageD[0] = timer.getElapsedTime();
						averageD[1] += averageD[0];
						System.out.println("Dijkstra's");
						visualize(le,array,graphSize);
						System.out.println("__________________________________\n");
						D.append(averageD[0] + ", ");
					}
					
					timer.reset();
					le = a.findGraph(graphSize, startPoint, endPoint, array);
					
					//if no path don't take
					if(le == null)
					{
						
					}else{
						
						averageA[0] = timer.getElapsedTime();
						averageA[1] += averageA[0];
//						System.out.println("A-Star");
//						visualize(le,array,graphSize);
//						System.out.println("__________________________________\n");
						A.append(averageA[0] + ", ");
					}
					
					
//					array = eg.toMatrixArrayTest(graphSize, numEdges);
//					gc.ConstructGraph(g, array, graphSize);
//					System.out.println("Num Edges: " + numEdges + "\nBefore D\n" + g);
//					gc.ConstructGraph(g, le);
//					System.out.println("\nAfter D\n" + g);
					
										
				}
				
				averageD[1] /= numRuns;
				averageA[1] /= numRuns;
				D.append(averageD[1] + "");
				A.append(averageA[1] + "");
				outputFile.append(D + System.getProperty("line.separator") + A + System.getProperty("line.separator"));
				D.setLength(0);		
				A.setLength(0);		
				System.out.printf("%2d%7d%13d%13d\n",j,(int)Math.pow(j,2),averageD[0]/1000,averageA[0]/1000);
			}
			outputFile.append(System.getProperty("line.separator"));
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
		
		
		long Prims = 0, Kruskals = 0;
		
		//find closest comparison after 50 tries
		for(int i = 0; i < 20; i++)
		{
			//average of 5 times
			for(int j = 0; j < 5; j++)
			{
				array = eg.toMatrixArray(graphSize, numEdges);
				timer.reset();
//				le = p.findGraph(graphSize, array);							
//				Prims += timer.getElapsedTime();
				
				le = eg.toList(graphSize, numEdges);
				gc.ConstructGraph(g, le);
				timer.reset();
//				le = k.findGraph(g);
//				Kruskals += timer.getElapsedTime();
				
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
	
	
	static void visualize(List<MyEdge> edges, double [][] array, int n)
	{
//		MyGraphConstructor gc = new MyGraphConstructor();
//		MyGraph g = new MyGraph();
//		gc.ConstructGraph(g, edges);
//		System.out.println(g);
		int node = 0;
		int side = (int)Math.pow(n, .5);
		MyEdge e = new MyEdge();
		for(int i = 0; i < side; i++)
		{
			System.out.print(" ");
			for(int j = 0; j < side-1; j++)
			{
				
				System.out.printf("%2d", node++);
				e.setEdge(node-1,node,0);
				if(edges.contains(e))
				{
					System.out.print(" ---");
				}else if(array[node-1][node] < 0)
				{
					System.out.print("  X ");
				}
				else{
					System.out.print("    ");
				}
			}
			System.out.printf("%2d\n", node);
			node -= side-1;
			
			for(int j = 0; j < side; j++)
			{
//				System.out.println("node = " + node + " side = " + side);
				e.setEdge(node,node+side,0);
				if(edges.contains(e))
				{
					System.out.print("  |   ");
				}else if(node+side < n && array[node][node+side] < 0)
				{
					System.out.print("  X   ");
				}else{
					System.out.print("      ");
				}
				node++;
			}
			System.out.println();
			
		}
		
		
		
	}

}
