package events;

public class EnterPasswordEvent extends AlarmSystemEvent {
	private static EnterPasswordEvent instance;

	/**
	 * Private for singleton
	 * 
	 */
	private EnterPasswordEvent() {

	}

	/**
	 * For the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static EnterPasswordEvent instance() {
		if (instance == null) {
			instance = new EnterPasswordEvent();
		}
		return instance;
	}
}
