package events;

public class ZoneCheckEvent extends AlarmSystemEvent {
	private static ZoneCheckEvent instance;

	/**
	 * Private for singleton
	 * 
	 */
	private ZoneCheckEvent() {

	}

	/**
	 * For the singleton pattern
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
