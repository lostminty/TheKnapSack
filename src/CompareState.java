

import java.util.Comparator;

public class CompareState implements Comparator<State>{
	
	int _pos;
	int[] _order;
	public CompareState(int i,int j, int k) {
		_order= new int[3];
		_order[0]=i;
		_order[1]=j;
		_order[2]=k;
		_pos= 0;
		
	}
	public int compare(State o1, State o2) {
		
		int comp = Float.compare(o1._variable[_order[_pos]], o2._variable[_order[_pos]]);
		_pos++;
		_pos=_pos%3;
		
		return comp;
		
	}
	
	public int anyCompare(State o1, State o2){
		
		
		if (o1._variable[0]> o2._variable[0] || o1._variable[1]> o2._variable[1] || o1._variable[2]> o2._variable[02])
		{
			return 1;
		}
		else{
			return -1;
		}
		
	}
}
