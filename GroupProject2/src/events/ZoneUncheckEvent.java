package events;

public class ZoneUncheckEvent extends AlarmSystemEvent {
	private static ZoneUncheckEvent instance;

	/**
	 * Private for singleton
	 * 
	 */
	private ZoneUncheckEvent() {

	}

	/**
	 * For the singleton pattern
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
