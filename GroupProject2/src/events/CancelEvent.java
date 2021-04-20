package events;

public class CancelEvent extends AlarmSystemEvent {
	private static CancelEvent instance;

	/**
	 * Private for singleton
	 * 
	 */
	private CancelEvent() {

	}

	/**
	 * For the singleton pattern
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
