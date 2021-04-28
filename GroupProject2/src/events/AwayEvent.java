package events;

/**
 * This class represents the Away arming operation.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class AwayEvent extends AlarmSystemEvent {
	private static AwayEvent instance;

	/**
	 * Private constructor for singleton
	 * 
	 */
	private AwayEvent() {

	}

	/**
	 * Provides an instance as it adheres to the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static AwayEvent instance() {
		if (instance == null) {
			instance = new AwayEvent();
		}
		return instance;
	}
}
