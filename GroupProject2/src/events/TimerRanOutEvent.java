package events;

/**
 * Represents the TimerRanOut event.
 *
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 */
public class TimerRanOutEvent extends AlarmSystemEvent {
	private static TimerRanOutEvent instance;

	/**
	 * Private constructor for singleton
	 * 
	 */
	private TimerRanOutEvent() {

	}

	/**
	 * Provides an instance as it adheres to the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static TimerRanOutEvent instance() {
		if (instance == null) {
			instance = new TimerRanOutEvent();
		}
		return instance;
	}
}