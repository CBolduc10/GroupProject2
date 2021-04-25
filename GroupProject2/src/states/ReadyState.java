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
		AlarmSystemContext.instance().setArmedStateValue(true);
		AlarmSystemContext.instance().changeState(WaitingState.instance());
	}

	/**
	 * handle away event
	 * 
	 */
	@Override
	public void handleEvent(AwayEvent event) {
		AlarmSystemContext.instance().setArmedStateValue(false);
		AlarmSystemContext.instance().changeState(WaitingState.instance());
	}

	/**
	 * handle zone unchecked event
	 * 
	 */
	@Override
	public void handleEvent(ZoneUncheckEvent event) {
		AlarmSystemContext.instance().decrement();
		AlarmSystemContext.instance().changeState(NotReadyState.instance());
	}

	@Override
	public void enter() {
		AlarmSystemContext.instance().showReady();
	}

	@Override
	public void leave() {

	}

}