package states;

import events.AwayEvent;
import events.StayEvent;
import events.ZoneUncheckEvent;

public class ReadyState extends AlarmSystemState {
	private static ReadyState instance;

	/**
	 * Private constructor for the singleton pattern
	 */
	private ReadyState() {
	}

	/**
	 * returns the instance
	 * 
	 * @return this object
	 */
	public static ReadyState instance() {
		if (instance == null) {
			instance = new ReadyState();
		}
		return instance;
	}

	/**
	 * handle stay event
	 * 
	 */

	@Override
	public void handleEvent(StayEvent event) {
		AlarmSystemContext.instance().changeState(WaitingState.instance());
	}

	/**
	 * handle away event
	 * 
	 */

	@Override
	public void handleEvent(AwayEvent event) {
		AlarmSystemContext.instance().changeState(WaitingState.instance());
	}

	/**
	 * handle zone unchecked event
	 * 
	 */

	@Override
	public void handleEvent(ZoneUncheckEvent event) {
		AlarmSystemContext.instance().changeState(NotReadyState.instance());
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