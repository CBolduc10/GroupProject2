package states;

import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;

/**
 * This class represents the Not Ready state.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class NotReadyState extends AlarmSystemState {
	private static NotReadyState instance;

	/**
	 * Private constructor for the singleton pattern
	 */
	private NotReadyState() {
	}

	/**
	 * Provides an instance as it adheres to the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static NotReadyState instance() {
		if (instance == null) {
			instance = new NotReadyState();
		}
		return instance;
	}

	/**
	 * Process zone check event and increment count. If count is 3, then this
	 * indicates all zone are checked and the context switches to the Ready
	 * state.
	 * 
	 * @param event
	 */
	@Override
	public void handleEvent(ZoneCheckEvent event) {
		AlarmSystemContext.instance().increment();
		if (AlarmSystemContext.instance().getCount() == 3) {
			AlarmSystemContext.instance().changeState(ReadyState.instance());
		}
	}

	/**
	 * Process zone un-check event and decrement count
	 * 
	 * @param event
	 */
	@Override
	public void handleEvent(ZoneUncheckEvent event) {
		AlarmSystemContext.instance().decrement();
	}

	/**
	 * Upon entering the state, the context indicates to the display to show the
	 * system is not ready.
	 */
	@Override
	public void enter() {
		AlarmSystemContext.instance().showNotReady();
	}

	/**
	 * Upon exiting the state, the context indicates to the display to show the
	 * system is ready.
	 */
	@Override
	public void leave() {
		AlarmSystemContext.instance().showReady();
	}

}
