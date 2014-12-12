package models;

import java.util.HashMap;

public class Data {

	private HashMap<String, Integer> integers = new HashMap<>();
	
	private HashMap<String, Boolean> booleans = new HashMap<>();

	public boolean isDeclaredVariable(String command) {
		return this.integers.containsKey(command) || this.booleans.containsKey(command);
	}
}
