public class HeapSort implements SortAlgorithm{ 
	private double[] array; 
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
        	child = largerChild(index);
        	exchange(index,child);
        	index=child;
        }
    }  
    
    public void sort(double [] array)
    { 
    	this.array = array; 
    	//heap largest at top
        heapify(array); 
        
        //after largest at top, put it at the end of the array and shrink the heap
        for(int i=size;i>0;i--){ 
            poll();
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
    	if(parent*2+1 < size)
    	{
    		//if the right child is greater return it
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
    }
    
    public void print(double [] array)
	{
		for(int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + ", ");
		}
		System.out.println();
	}
}