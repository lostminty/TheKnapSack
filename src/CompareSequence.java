import java.util.Comparator;


public class CompareSequence implements Comparator<Sequence>  {

	int _n;
	public CompareSequence(int n) {
		_n=n;
	}

	@Override
	public int compare(Sequence arg0, Sequence arg1) {
		
		if (_n==0)
			return Float.compare(arg0._i,arg1._i);
		else
			return Float.compare(arg0._j, arg1._j);
	}

}
