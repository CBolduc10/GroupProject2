package states;

import events.AwayEvent;
import events.StayEvent;
import events.ZoneUncheckEvent;

public class ReadyState extends AlarmSystemState {
	private static ReadyState instance;
	private boolean armedState;

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
		armedState = true;
	}

	/**
	 * handle away event
	 * 
	 */

	@Override
	public void handleEvent(AwayEvent event) {
		AlarmSystemContext.instance().changeState(WaitingState.instance());
		armedState = false;
	}

	/**
	 * handle zone unchecked event
	 * 
	 */

	@Override
	public void handleEvent(ZoneUncheckEvent event) {
		NotReadyState.instance().setCount(NotReadyState.instance().getCount() - 1);
		AlarmSystemContext.instance().changeState(NotReadyState.instance());
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		AlarmSystemContext.instance().showReady();
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub

	}

	public boolean isArmedState() {
		return armedState;
	}

}
