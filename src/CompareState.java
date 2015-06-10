

import java.util.Comparator;

public class CompareState implements Comparator<State>{
	
	int _pos;
	int[] _cycle;
	public CompareState(int[] cycle) {
		_pos = 0;
		_cycle=cycle;
		
	}
	public int compare(State o1, State o2) {
		_pos=_pos%3;
		_pos++;
		return Float.compare(o1._variable[_cycle[_pos-1]], o2._variable[_cycle[_pos-1]]);
		
	}
	
	public int compareLast(State o1, State o2){
		return Float.compare(o1._variable[_cycle[_pos-1]], o2._variable[_cycle[_pos-1]]);
	}
	
	
	
	
}
