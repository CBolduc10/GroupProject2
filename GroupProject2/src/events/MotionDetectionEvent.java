package events;

/**
 * This class represents the motion detection operation/event.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class MotionDetectionEvent extends AlarmSystemEvent {
	private static MotionDetectionEvent instance;

	/**
	 * Private constructor for singleton
	 * 
	 */
	private MotionDetectionEvent() {

	}

	/**
	 * Provides an instance as it adheres to the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static MotionDetectionEvent instance() {
		if (instance == null) {
			instance = new MotionDetectionEvent();
		}
		return instance;
	}
}
