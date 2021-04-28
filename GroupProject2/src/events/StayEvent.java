package events;

/**
 * This class represents the Stay arming operation.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class StayEvent extends AlarmSystemEvent {
	private static StayEvent instance;

	/**
	 * Private constructor for singleton
	 * 
	 */
	private StayEvent() {

	}

	/**
	 * Provides an instance as it adheres to the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static StayEvent instance() {
		if (instance == null) {
			instance = new StayEvent();
		}
		return instance;
	}
}
