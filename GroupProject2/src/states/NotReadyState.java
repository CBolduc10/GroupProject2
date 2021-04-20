package states;

import events.ZoneCheckEvent;

public class NotReadyState extends AlarmSystemState {
	private static NotReadyState instance;
	private int count = 0;

	/**
	 * Private constructor for the singleton pattern
	 */
	private NotReadyState() {
	}

	/**
	 * returns the instance
	 * 
	 * @return this object
	 */
	public static NotReadyState instance() {
		if (instance == null) {
			instance = new NotReadyState();
		}
		return instance;
	}

	/**
	 * handle ZoneCheckEvent event
	 * 
	 */

	@Override
	public void handleEvent(ZoneCheckEvent event) {
		count++;
		if (count == 3) {
			AlarmSystemContext.instance().changeState(ReadyState.instance());
		}
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub

	}

}
