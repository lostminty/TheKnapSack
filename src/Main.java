import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author Ben Cartner 0124733
 * This is an experimental program. It's main feature was using cycling comparators that alters the sequence of a greedy comparator.
 * If I attempt this again, I'll be using the zero state and the goal state to draw a vector. Then transform the space with cylindrical polar matrix.
 * Then employ cyclic comparisons using vector representations.
 */
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

		pool = new ArrayList<Sequence>(); //the gene pool. Generated sequences from the NextStates method
		data = new ArrayList<State>(); //input from the dat file
		initial = new State(0, 0, 0, 0); //zero performing state. Later I find the closest representatives

		goal = new State(Float.parseFloat(args[0]), Float.parseFloat(args[1]),
				Float.parseFloat(args[2]), 0f); //maximum state completely full ship..

		FileReader read = new FileReader(args[3]);
		BufferedReader readin = new BufferedReader(read);

		while (readin.ready()) {
			String[] strings = readin.readLine().split(" ");

			data.add(new State(Float.parseFloat(strings[0]), Float
					.parseFloat(strings[1]), Float.parseFloat(strings[2]),
					Float.parseFloat(strings[3])));
		}
		readin.close();
		ArrayList<State> states= States(); //establish the 3 'closest' states. May be realistic to scale this as a vector length.
		NextStates(states); //attempt to traverse the cyclic greedy space that starts off from the 'closest' states. Generates sequences
		
		
		while (pool.size() >6)
			pool = GetChildren(pool); //cross breed the Sequences until no one is left to breed (can only cross with sequences of the same generation)
		
		
		
	
		
		for (Sequence seq: pool)
			for(State s: seq._sequence)
			{
				System.out.println("v1="+s._variable[0]+" v2="+s._variable[1]+ " v3="+s._variable[2]);
			}
		
		
		

	}
	
	static ArrayList<State> States(){
		ArrayList<State> states = new ArrayList<State>();
		
		
		Comparator<State> compState0 =new CompareStateStatic(0);
		Comparator<State> compState1 =new CompareStateStatic(1);
		Comparator<State> compState2 =new CompareStateStatic(2);
		Collections.sort(data,compState0);
		states.add(data.get(0));
		Collections.sort(data,compState1);
		states.add(data.get(0));
		Collections.sort(data,compState2);
		states.add(data.get(0));

		return states;
	}
	
	static int[][] Cycle(){ //cumbersome yes. 
		
		
		int[][] cycles = new int[6][3];
		
		int[] cycle = new int[3];
		
		cycle[0]=0;
		cycle[1]=1;
		cycle[2]=2;
		cycles[0]= cycle;
		cycle[0]=0;
		cycle[1]=2;
		cycle[2]=1;
		cycles[1]= cycle;
		cycle[0]=1;
		cycle[1]=0;
		cycle[2]=2;
		cycles[2]=cycle;
		cycle[0]=1;
		cycle[1]=2;
		cycle[2]=0;
		cycles[3]=cycle;
		cycle[0]=2;
		cycle[1]=0;
		cycle[2]=1;
		cycles[4]=cycle;
		cycle[0]=2;
		cycle[1]=1;
		cycle[2]=0;
		cycles[5]=cycle;
		
		
		return cycles;
	}

	static void NextStates(ArrayList<State> monads) { 

		CompareState comp;
		
		ArrayList<State> _copy;
		
		
		Sequence states;

		int[][] cycles = Cycle();
		
		int i=0;
		for (int[] cycle: cycles) {
		
				 {
					 _copy=new ArrayList<State>();
					 
					 for (State s: data)
						 _copy.add(s);

					if (i%2==0 && monads.size()!=0){
						current = monads.get(0);
						monads.remove(0);
					}
					else
						break;
					ArrayList<State> path = new ArrayList<State>();
					
					path.add(current);
					
					states = new Sequence(path);
					comp = new CompareState(cycle);

					do  {
						states._sequence.add(current);
						Collections.sort(_copy, comp);
						current = _copy.get(0);
						_copy.remove(current);
					} while (comp.compareLast(current, goal) < 0);

					pool.add(states);
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
