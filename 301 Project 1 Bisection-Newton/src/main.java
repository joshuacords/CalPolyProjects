
public class main {

	public static void main(String[] args) {
		Bisection b = new Bisection();
		Newton n = new Newton();
		double x;
		x = b.b(0, 100.0, 15, .5);
		x = n.n(x, 15, 1E-8, 1E-5);
		System.out.println("Final answer: "+ x);
	}

}
