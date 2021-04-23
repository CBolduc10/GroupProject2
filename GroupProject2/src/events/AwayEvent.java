package events;

public class AwayEvent extends AlarmSystemEvent {
	private static AwayEvent instance;
	private boolean value = true;

	/**
	 * Private for singleton
	 * 
	 */
	private AwayEvent() {

	}

	/**
	 * For the singleton pattern
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
