package password;

import java.util.LinkedList;
import java.util.List;

public class Password {
	private static Password instance;
	private List<Integer> password = new LinkedList<Integer>();
	private List<Integer> entry = new LinkedList<Integer>();
	private String passwordEntry = "";

	private Password() {
		password.add(1);
		password.add(2);
		password.add(3);
		password.add(4);
	}

	public static Password instance() {
		if (instance == null) {
			instance = new Password();
		}
		return instance;
	}

	public boolean entry(int number) {
		passwordEntry += number;
		entry.add(number);
		if (entry.size() == password.size()) {
			if (entry.equals(password)) {
				entry.clear();
				return true;
			} else {
				clear();
				entry.clear();
				return false;
			}
		}
		return false;
	}

	public void clear() {
		this.passwordEntry = "";
	}

	public String toString() {
		return passwordEntry;
	}
}
