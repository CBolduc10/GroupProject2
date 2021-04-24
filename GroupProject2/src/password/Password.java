package password;

import java.util.LinkedList;
import java.util.List;

public class Password {
	private List<Integer> password = new LinkedList<Integer>();
	private List<Integer> entry = new LinkedList<Integer>();
	private String passwordEntry = "";

	public Password() {
		password.add(1);
		password.add(2);
		password.add(3);
		password.add(4);
	}

	public boolean entry(int number) {
		passwordEntry += number;
		if (entry.size() < password.size()) {
			entry.add(number);
		}
		if (entry.size() == password.size()) {
			if (entry.equals(password)) {
				entry.clear();
				return true;
			} else {
				entry.clear();
				passwordEntry = "";
				return false;
			}
		}
		return false;
	}

	public String toString() {
		return passwordEntry;
	}
}
