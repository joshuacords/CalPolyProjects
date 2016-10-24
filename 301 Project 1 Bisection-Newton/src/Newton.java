
public class Newton {
	double n(double x, int nmax, double machE, double dError)
	{
		double fx, fp, d,x0;
		fx = function(x);
		for(int n = 1; n<nmax; n++)
		{
			fp = fprime(x);
			if(Math.abs(fp)<dError)
			{
				System.out.println("Error: derivative too small at " + fp);
				return fx;
			}
			d = fx/fp;
			x0 = x;
			x = x - d;
			fx = function(x);
			System.out.printf("Newton Loop #%d xn = %11.9f x0 = %11.9f error = %9.9f%n",n,x,x0,Math.abs(d));
			if(Math.abs(d) < machE)
			{
//				System.out.println("Convergence");
				return x;
			}
		}
		return x;
		
		
	}
	
	double function(double x)
	{
		//calculates f(x) = (x-30)^2(x-60)^2(x^2-2)-10
		double y;
		y = -6480010+648000*x+3216600*Math.pow(x,2)-323640*Math.pow(x,3)+11698*Math.pow(x,4)-180*Math.pow(x, 5) + Math.pow(x, 6);
		return y;
	}
	double fprime(double x)
	{
		//calculates f'(x) = (x-30)^2(x-60)^2(x^2-2)-10
		double y;
		y = 648000+6433200*x-970920*Math.pow(x,2)+46792*Math.pow(x,3)-900*Math.pow(x, 4) + 6*Math.pow(x, 5);
		return y;
	}
}
