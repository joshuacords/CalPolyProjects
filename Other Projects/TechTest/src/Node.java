public class Node 
{ 
 //LINK TO THE NEXT NODE  
public Node next; 

 //CONTENT OF THIS NODE  
private int content1; 
private int content2; 
// … there are many more members here 

	
// Feel free to add helper functions if needed here. 

	// Note removalRequests_Length is the same as removalRequests.Length 
	static public Node Remove( Node firstNode, int[] removalRequests, int removalRequests_Length ) 
	{
		int nodeIndex = 1;	//start 1 to not remove firstNode
		Node currentNode = firstNode.next;	//keeps track of current node
		Node lastNode = firstNode;
		
		for(int i = 0; i < removalRequests_Length; i++)
		{
			//iterates through list until removal request node is found
			while(removalRequests[i]!=nodeIndex)
			{
				lastNode = currentNode;
				currentNode = currentNode.next;
				nodeIndex++;
				if(currentNode.next == null)
				{
					System.out.println("Error, node to be removed not found");
					break;
				}
			}
			
			//if node is next to be removed: remove node
			if(removalRequests[i]==nodeIndex)
			{
				lastNode.next = currentNode.next;
				
			}
			
			currentNode = currentNode.next;
			nodeIndex++;
		}
		
		return firstNode; 
	} 
	
}
