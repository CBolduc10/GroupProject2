package events;

public class StayEvent extends AlarmSystemEvent {
	private static StayEvent instance;
	private boolean value = false;

	/**
	 * Private for singleton
	 * 
	 */
	private StayEvent() {

	}

	/**
	 * For the singleton pattern
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
