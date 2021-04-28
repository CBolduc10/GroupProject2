package events;

/**
 * This class represents the zone un-check operation/event.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class ZoneUncheckEvent extends AlarmSystemEvent {
	private static ZoneUncheckEvent instance;

	/**
	 * Private constructor for singleton
	 * 
	 */
	private ZoneUncheckEvent() {

	}

	/**
	 * Provides an instance as it adheres to the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static ZoneUncheckEvent instance() {
		if (instance == null) {
			instance = new ZoneUncheckEvent();
		}
		return instance;
	}
}
