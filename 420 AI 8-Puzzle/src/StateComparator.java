import java.util.Comparator;

public class StateComparator implements Comparator<MyVertex>{

	@Override
	public int compare(MyVertex o1, MyVertex o2) {
		if(o1 == null || o2 == null){
			return 0;
		}

		if(o1.getFCost() < o2.getFCost()){
			return -1;
		} else if(o1.getFCost() > o2.getFCost()){
			return 1;
		}

		return 0;
	}

}
