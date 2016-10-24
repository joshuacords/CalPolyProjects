
public class Bisection {

	double b(double a, double b, int nmax, double machE)
	{
		double fa, fb, fc, error,c=0;
		fa = function(a);
		fb = function(b);
		if(Math.signum(fa)==Math.signum(fb))
		{
			System.out.println("Error in starting, a & b have same sign");
			return 0;
		}
		error = b-a;
		for(int n = 1;n<nmax;n++)
		{
			error = error/2;
			c = a + error;
			fc=function(c);
			System.out.printf("Bisection Loop #%d c = %9f a = %9f b = %10f error = %9f%n",n,c,a,b,error);
			if(error<machE)
			{
//				System.out.println("Convergence");
				return c;
			}
			if(Math.signum(fa)!=Math.signum(fc))
			{
				b = c;
				fb = fc;
			}else{
				a = c;
				fa = fc;
			}
		}
		return c;
		
	}
	
	double function(double x)
	{
		//calculates f(x) = (x-30)^2(x-60)^2(x^2-2)-10
		double y;
		y = -6480010+648000*x+3216600*Math.pow(x,2)-323640*Math.pow(x,3)+11698*Math.pow(x,4)-180*Math.pow(x, 5) + Math.pow(x, 6);
		return y;
	}
}
