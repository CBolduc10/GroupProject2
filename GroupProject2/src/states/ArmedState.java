package states;

import events.CancelEvent;
import events.MotionDetectionEvent;
import events.ZoneUncheckEvent;

public class ArmedState extends AlarmSystemState {
	private static ArmedState instance;
	private boolean armedStateValue;

	/**
	 * Private constructor for the singleton pattern
	 */
	private ArmedState() {
	}

	/**
	 * returns the instance
	 * 
	 * @return this object
	 */
	public static ArmedState instance() {
		if (instance == null) {
			instance = new ArmedState();
		}
		return instance;
	}

	@Override
	public void handleEvent(ZoneUncheckEvent event) {
		NotReadyState.count--;
		if (armedStateValue) {
			AlarmSystemContext.instance().changeState(AlarmState.instance());
		} else {
			AlarmSystemContext.instance().changeState(WarningState.instance());
		}
	}

	@Override
	public void handleEvent(CancelEvent event) {
		AlarmSystemContext.instance().changeState(CancelState.instance());
	}

	@Override
	public void handleEvent(MotionDetectionEvent event) {
		if (!armedStateValue) {
			AlarmSystemContext.instance().changeState(WarningState.instance());
		}
	}

	@Override
	public void enter() {
		armedStateValue = ReadyState.armedStateValue;
		if (armedStateValue) {
			AlarmSystemContext.instance().showStay();
		} else {
			AlarmSystemContext.instance().showAway();
		}
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub

	}
}
