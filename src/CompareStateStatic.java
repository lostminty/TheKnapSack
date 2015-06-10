import java.util.Comparator;


public class CompareStateStatic implements Comparator<State> {

	int _n;
	public CompareStateStatic(int n) {
		
		_n=n;
		// TODO Auto-generated constructor stub
	}
	@Override
	public int compare(State arg0, State arg1) {
		// TODO Auto-generated method stub
		return Float.compare(arg0._variable[_n], arg1._variable[_n]);
	}

}
