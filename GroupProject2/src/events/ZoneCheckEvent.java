package events;

/**
 * This class represents the zone check operation/event.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class ZoneCheckEvent extends AlarmSystemEvent {
	private static ZoneCheckEvent instance;

	/**
	 * Private constructor for singleton
	 * 
	 */
	private ZoneCheckEvent() {

	}

	/**
	 * Provides an instance as it adheres to the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static ZoneCheckEvent instance() {
		if (instance == null) {
			instance = new ZoneCheckEvent();
		}
		return instance;
	}
}
