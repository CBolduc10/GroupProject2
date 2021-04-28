package states;

import events.EnterPasswordEvent;
import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;
import password.Password;

/**
 * This class represents the alarm state (security breached).
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class AlarmState extends AlarmSystemState {
	private static AlarmState instance;

	/**
	 * Private constructor for singleton
	 */
	private AlarmState() {
	}

	/**
	 * Provides an instance as it adheres to the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static AlarmState instance() {
		if (instance == null) {
			instance = new AlarmState();
		}
		return instance;
	}

	/**
	 * Processes a password entry event by adding the corresponding numeric
	 * values to be compared against the stored password. If the entry method
	 * for Password returns true, then the system reverts to the either the Not
	 * Ready or Ready state according to the count.
	 * 
	 * @param event
	 * @param number numeric value corresponding to event origin
	 */
	@Override
	public void handleEvent(EnterPasswordEvent event, int number) {
		if (Password.instance().entry(number)) {
			if (AlarmSystemContext.instance().getCount() < 3) {
				AlarmSystemContext.instance()
						.changeState(NotReadyState.instance());
			} else {
				AlarmSystemContext.instance()
						.changeState(ReadyState.instance());
			}
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
	 * Process zone check event and decrement count
	 * 
	 * @param event
	 */
	@Override
	public void handleEvent(ZoneCheckEvent event) {
		AlarmSystemContext.instance().increment();
	}

	/**
	 * Upon entering alarm state, context indicates display should show the
	 * system has been breached.
	 */
	@Override
	public void enter() {
		AlarmSystemContext.instance().showBreached();
	}

	/**
	 * Upon leaving the alarm state, the Password string is cleared.
	 */
	@Override
	public void leave() {
		Password.instance().clear();
	}

}
