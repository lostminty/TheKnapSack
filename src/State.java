


public class State {
	
	float[] _variable = new float[3];
	
	State(float volume, float weight,float cost,float value){
		_variable[0]=volume;
		_variable[1]=weight;
		_variable[2]=(value-cost);
		
	}


}
