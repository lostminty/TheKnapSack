import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	static State current, goal, initial;
	static ArrayList<State> data;
	static ArrayList<Sequence> pool;

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		// TODO Auto-generated method stub

		pool = new ArrayList<Sequence>();
		data = new ArrayList<State>();
		initial = new State(0, 0, 0, 0);

		goal = new State(Float.parseFloat(args[0]), Float.parseFloat(args[1]),
				Float.parseFloat(args[2]), 0f);

		FileReader read = new FileReader(args[3]);
		BufferedReader readin = new BufferedReader(read);

		while (readin.ready()) {
			String[] strings = readin.readLine().split(" ");

			data.add(new State(Float.parseFloat(strings[0]), Float
					.parseFloat(strings[1]), Float.parseFloat(strings[2]),
					Float.parseFloat(strings[3])));
		}
		readin.close();

		NextStates();
		
		
		while (pool.size() >1)
			pool = GetChildren(pool);
		
		
		
		Sequence theAnswer = pool.get(0);
		
		for(State s: theAnswer._sequence)
		{
			System.out.println("v1="+s._variable[0]+" v2="+s._variable)
		}
		
		
		

	}

	static void NextStates() {

		CompareState comp;
		
		Sequence states;

		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 2; k++) {

					current = initial;
					ArrayList<State> path = new ArrayList<State>();
					path.add(current);
					
					states = new Sequence(path);
					comp = new CompareState(i%3,(i*j)%3,(i*j*k)%3);

					while (comp.anyCompare(current, goal) < 0) {
						states._sequence.add(current);
						Collections.sort(data, comp);
						current = data.get(0);
					}

					pool.add(states);
				}
			}
		}

	}
	
	static ArrayList<Sequence> GetChildren( ArrayList<Sequence> _parents){
		
		Comparator<Sequence> iCom = new CompareSequence(0);
		Comparator<Sequence> jCom = new CompareSequence(1);
		
		;
		ArrayList<Sequence> _children= new ArrayList<Sequence>();
		while (_parents.size() >1)
		{
		Collections.sort(_parents,iCom);
		Sequence mother = _parents.get(0);
		_parents.remove(mother);
		Collections.sort(_parents,jCom);
		Sequence father = _parents.get(0);
		_parents.remove(father);
		
		_children.add(Cross(mother,father));
		
		}
		
		
		return _children;
		
	}
	
	static Sequence Cross(Sequence mother, Sequence father){
		
		int size;
		State current;
		if (mother._sequence.size()>= father._sequence.size())
			size = mother._sequence.size();
		else
			size = father._sequence.size();
		
		int i=0;
		ArrayList<State> childSequence = new ArrayList<State>();
		while (childSequence.size() < size)
		{
			if (childSequence.size()%2 ==0 &&  mother._sequence.size()>childSequence.size())
				current = mother._sequence.get(childSequence.size());
			else if (childSequence.size()%2 ==1 &&  father._sequence.size()>childSequence.size() )
				current = father._sequence.get(childSequence.size());
			else if (mother._sequence.size()>= father._sequence.size())
				current = mother._sequence.get(childSequence.size());
			else
				current = father._sequence.get(childSequence.size());
			
			childSequence.add(current);
		}
		
		return new Sequence(childSequence);
	}

}
