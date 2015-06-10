import java.util.ArrayList;


public class Sequence {

	float _i,_j;
	
	boolean _bred;
	ArrayList<State> _sequence;
	public Sequence(ArrayList<State> sequence) {
		
		_bred=false;
		_sequence= sequence;
		_i=0;_j=0;
		for (State s: _sequence)
		{
			_i+=s._variable[0];
			_j+=s._variable[1];
		}
	}
	
	public void Bred(){
		_bred=true;
	}

}
