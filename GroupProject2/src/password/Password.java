package password;

import java.util.LinkedList;
import java.util.List;

public class Password {
	private List<Integer> password = new LinkedList<Integer>();

	public Password() {
		password.add(1);
		password.add(2);
		password.add(3);
		password.add(4);
	}

	public int size() {
		return password.size();
	}

	public List<Integer> getPassword() {
		return password;
	}

}
