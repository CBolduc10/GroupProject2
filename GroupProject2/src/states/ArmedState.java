package states;

import events.CancelEvent;
import events.MotionDetectionEvent;
import events.ZoneUncheckEvent;

/**
 * This class represents the armed state.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class ArmedState extends AlarmSystemState {
	private static ArmedState instance;

	/**
	 * Private constructor for the singleton pattern
	 */
	private ArmedState() {
	}

	/**
	 * Provides an instance as it adheres to the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static ArmedState instance() {
		if (instance == null) {
			instance = new ArmedState();
		}
		return instance;
	}

	/**
	 * Processes a zone un-check event, decrements the context's count, and
	 * switches context to the alarm state or warning state according to the
	 * armed state value stored in context, so stay(true) or away(false)
	 * respectively.
	 * 
	 * @param event
	 */
	@Override
	public void handleEvent(ZoneUncheckEvent event) {
		AlarmSystemContext.instance().decrement();
		if (AlarmSystemContext.instance().getArmedStateValue()) {
			AlarmSystemContext.instance().changeState(AlarmState.instance());
		} else {
			AlarmSystemContext.instance().changeState(WarningState.instance());
		}
	}

	/**
	 * Processes a cancel event to switch to cancel state
	 * 
	 * @param event
	 */
	@Override
	public void handleEvent(CancelEvent event) {
		AlarmSystemContext.instance().changeState(CancelState.instance());
	}

	/**
	 * Processes a motion detection event if the armed state value is currently
	 * set to away(false). If so, context switches to warning state.
	 */
	@Override
	public void handleEvent(MotionDetectionEvent event) {
		if (!AlarmSystemContext.instance().getArmedStateValue()) {
			AlarmSystemContext.instance().changeState(WarningState.instance());
		}
	}

	/**
	 * Upon entering the state, the context indicates to the display whether to
	 * update to away or stay mode of the armed state, according to the value
	 * stored in context.
	 */
	@Override
	public void enter() {
		if (AlarmSystemContext.instance().getArmedStateValue()) {
			AlarmSystemContext.instance().showStay();
		} else {
			AlarmSystemContext.instance().showAway();
		}
	}

	@Override
	public void leave() {

	}
}
