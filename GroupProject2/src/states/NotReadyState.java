package states;

import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;

public class NotReadyState extends AlarmSystemState {
	private static NotReadyState instance;

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
		AlarmSystemContext.instance().increment();
		System.out.println(AlarmSystemContext.instance().getCount());
		if (AlarmSystemContext.instance().getCount() == 3) {
			AlarmSystemContext.instance().changeState(ReadyState.instance());
		}
	}

	@Override
	public void handleEvent(ZoneUncheckEvent event) {
		AlarmSystemContext.instance().decrement();
		System.out.println(AlarmSystemContext.instance().getCount());
	}

	@Override
	public void enter() {
		AlarmSystemContext.instance().showNotReady();
		System.out.println(AlarmSystemContext.instance().getCount());
	}

	@Override
	public void leave() {
		AlarmSystemContext.instance().showReady();
	}

}
