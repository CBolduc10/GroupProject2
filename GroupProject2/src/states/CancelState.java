package states;

import events.EnterPasswordEvent;
import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;
import password.Password;

/**
 * This class represents the cancel state.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class CancelState extends AlarmSystemState {
	private static CancelState instance;

	/**
	 * Private constructor for the singleton pattern
	 */
	private CancelState() {
	}

	/**
	 * Provides an instance as it adheres to the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static CancelState instance() {
		if (instance == null) {
			instance = new CancelState();
		}
		return instance;
	}

	/**
	 * Processes the password entry event and passes the corresponding numeric
	 * value to the entry method of password to be checked. The display is
	 * indicated to either display the enter password prompt if the password
	 * entry is empty or the password string value if entry is in progress. If
	 * the password is accepted and returned true, the context switches the
	 * state to either the Not Ready or Ready state according to count.
	 * 
	 * @param event
	 * @param number numeric value corresponding to event origin
	 */
	@Override
	public void handleEvent(EnterPasswordEvent event, int number) {
		boolean entry = Password.instance().entry(number);
		if (Password.instance().toString().isEmpty()) {
			AlarmSystemContext.instance().showEnterPassword();
		} else {
			AlarmSystemContext.instance()
					.showPassword(Password.instance().toString());
		}
		if (entry) {
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
	 * Process zone check event and increment count
	 * 
	 * @param event
	 */
	@Override
	public void handleEvent(ZoneCheckEvent event) {
		AlarmSystemContext.instance().decrement();
	}

	/**
	 * Upon entering the state, context indicates that the display should prompt
	 * user to enter a password.
	 */
	@Override
	public void enter() {
		AlarmSystemContext.instance().showEnterPassword();
	}

	/**
	 * Upon exiting the state, the Password string is cleared.
	 */
	@Override
	public void leave() {
		Password.instance().clear();
	}

}
