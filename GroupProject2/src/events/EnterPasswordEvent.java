package events;

/**
 * This class represents the enter password operation.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class EnterPasswordEvent extends AlarmSystemEvent {
	private static EnterPasswordEvent instance;

	/**
	 * Private constructor for singleton
	 * 
	 */
	private EnterPasswordEvent() {

	}

	/**
	 * Provides an instance as it adheres to the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static EnterPasswordEvent instance() {
		if (instance == null) {
			instance = new EnterPasswordEvent();
		}
		return instance;
	}
}
