package events;

/**
 * This class represents the cancel operation.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class CancelEvent extends AlarmSystemEvent {
	private static CancelEvent instance;

	/**
	 * Private constructor for singleton
	 * 
	 */
	private CancelEvent() {

	}

	/**
	 * Provides an instance as it adheres to the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static CancelEvent instance() {
		if (instance == null) {
			instance = new CancelEvent();
		}
		return instance;
	}
}
