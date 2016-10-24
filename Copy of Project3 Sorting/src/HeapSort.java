public class HeapSort implements SortAlgorithm{ 
	private double[] array; 
    private SortTimer t;
    private int size; 				//heap size
    
    //heap with largest on top for sorting purposes
    
    private void heapify(double [] array)
    { 
        size=array.length; 
        for(int i=size/2;i>=0;i--)
        { 
            siftDown(i); 
        } 
    } 
    
    private void siftDown(int a)
    { 
    	int index = a, child;
        //if parent has at least one child and it is smaller than its larger child then swap
        while(index < size/2 &&  array[largerChild(index)] > array[index])
        {
        	t.addComparison();
        	child = largerChild(index);
        	exchange(index,child);
        	index=child;
        }
    }  
    
    public void sort(double [] array, SortTimer t)
    { 
    	this.t = t;
    	this.array = array; 
    	//heap largest at top
        heapify(array); 
        
        //after largest at top, put it at the end of the array and shrink the heap
        for(int i=size;i>0;i--){ 
            poll();
            //print(array);
        } 
    } 
    
    private void poll()
    {
    	exchange(0,--size);
    	siftDown(0);
    }

    private int largerChild(int parent)		//assume the parent has at least 1 child
    {
    	//make sure there's 2 children
    	//System.out.println("size=" + size + " left=" + array[parent*2] + " right=" + array[parent*2+1]);
    	t.addComparison();
    	if(parent*2+1 < size)
    	{
    		//if the right child is greater return it
    		t.addComparison();
        	if(array[parent*2] < array[parent*2+1]) return parent*2+1;
    	}
    	//else return the only child's index
    	return parent*2;
    }
    
    
    private void exchange(int i, int j)
    { 
        double temp = array[i]; 
        array[i] = array[j]; 
        array[j] = temp; 
        t.addMoves(3);
    }
    
    public void print(double [] array)
	{
		for(int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + ", ");
		}
		System.out.println();
	}
    
    /*
     * private double[] array; 
    private SortTimer t;
    private int n; 				//heap size
    private int left; 
    private int right; 
    private int largest;		//heap with largest on top for sorting purposes
    
    private void heapify(double [] array)
    { 
        n=array.length-1; 
        for(int i=n/2;i>=0;i--)
        { 
            siftDown(array, i); 
        } 
    } 
    
    private void siftDown(double[] array, int i)
    { 
    	//assign children
        left=2*i; 
        right=2*i+1; 
        
        //check if left is off the array and if left child is larger than parent
        if(left <= n && array[left] > array[i])
        {
        	largest=left; 
        	t.addComparison();
        }
        else largest=i; 
        
        //check if right is off the array and if right child is larger than parent
        if(right <= n && array[right] > array[largest])
        {
        	largest=right; 
        	t.addComparison();
        }
        
        if(largest!=i)
        { 
            exchange(i,largest); 
            siftDown(array, largest); 
        } 
    } 
    
    private void exchange(int i, int j)
    { 
        double temp = array[i]; 
        array[i] = array[j]; 
        array[j] = temp; 
        t.addMove();
    } 
    
    public void sort(double [] array, SortTimer t)
    { 
    	this.t = t;
    	this.array = array; 
    	//heap largest at top
        heapify(array); 
        
        //after largest at top, put it at the end of the array and shrink the heap
        for(int i=n;i>0;i--){ 
            exchange(0, i); 
            n--;
            siftDown(array, 0);
        } 
    } 
    
    public void print(double [] array)
	{
		for(int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + ", ");
		}
		System.out.println();
	}
	*/
    
    
}