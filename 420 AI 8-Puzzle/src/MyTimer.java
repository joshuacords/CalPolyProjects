
public class MyTimer {

	private long time;
	public MyTimer()
	{
		time = System.nanoTime();
	}

	public void reset()
	{
		time = System.nanoTime();
	}

	public long getElapsedTime()
	{
		return System.nanoTime() -  time;
	}

}

