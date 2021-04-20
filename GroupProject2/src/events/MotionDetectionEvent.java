package events;

public class MotionDetectionEvent extends AlarmSystemEvent {
	private static MotionDetectionEvent instance;

	/**
	 * Private for singleton
	 * 
	 */
	private MotionDetectionEvent() {

	}

	/**
	 * For the singleton pattern
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
