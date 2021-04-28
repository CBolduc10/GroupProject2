package password;

import java.util.LinkedList;
import java.util.List;

/**
 * The Password is a Singleton class that compares entered integer values from
 * the user to a predetermined password to indicate successful or unsuccessful
 * password entry. It also provides a toString() method of the digits entered
 * thus far.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class Password {
	private static Password instance;
	private List<Integer> password = new LinkedList<Integer>();
	private List<Integer> entry = new LinkedList<Integer>();
	private String passwordEntry = "";

	/**
	 * Private constructor for singleton. Adds the predetermined password to a
	 * list (linked list) for comparison.
	 */
	private Password() {
		password.add(1);
		password.add(2);
		password.add(3);
		password.add(4);
	}

	/**
	 * Provides an instance as it adheres to the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static Password instance() {
		if (instance == null) {
			instance = new Password();
		}
		return instance;
	}

	/**
	 * This method simultaneously compares the entered digit to the
	 * predetermined password while updating the password entry string to be use
	 * for display purposes.
	 * 
	 * @param number the number entered by the user
	 * @return true if the password was accepted, or false if digits are still
	 *         being entered or the entry does not match the password.
	 */
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

	/**
	 * Method to clear the password entry string.
	 */
	public void clear() {
		this.passwordEntry = "";
	}

	/**
	 * Method to return the password entered (for display purposes)
	 */
	public String toString() {
		return passwordEntry;
	}
}
