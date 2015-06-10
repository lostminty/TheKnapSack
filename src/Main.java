import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	static State current, goal, initial;
	static ArrayList<State> data;
	static ArrayList<ArrayList<State>> pool;

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		// TODO Auto-generated method stub

		pool = new ArrayList<ArrayList<State>>();
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

	}

	static void NextStates() {

		CompareState comp;

		ArrayList<State> states;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 2; k++) {

					current = initial;
					states = new ArrayList<State>();
					comp = new CompareState(i%3,(i*j)%3,(i*j*k)%3);

					while (comp.anyCompare(current, goal) < 0) {
						states.add(current);
						Collections.sort(data, comp);
						current = data.get(0);
					}

					pool.add(states);
				}
			}
		}

	}

}
