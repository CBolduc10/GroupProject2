package states;

import events.EnterPasswordEvent;
import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;
import password.Password;

public class CancelState extends AlarmSystemState {
	private static CancelState instance;
	private Password password = new Password();

	/**
	 * Private constructor for the singleton pattern
	 */
	private CancelState() {
	}

	/**
	 * returns the instance
	 * 
	 * @return this object
	 */
	public static CancelState instance() {
		if (instance == null) {
			instance = new CancelState();
		}
		return instance;
	}

	@Override
	public void handleEvent(EnterPasswordEvent event, int number) {
		boolean entry = password.entry(number);
		if (password.toString().isEmpty()) {
			AlarmSystemContext.instance().showEnterPassword();
		} else {
			AlarmSystemContext.instance().showPassword(password.toString());
		}
		if (entry) {
			if (NotReadyState.count < 3) {
				AlarmSystemContext.instance()
						.changeState(NotReadyState.instance());
			} else {
				AlarmSystemContext.instance()
						.changeState(ReadyState.instance());
			}
		}
	}

	@Override
	public void handleEvent(ZoneUncheckEvent event) {
		NotReadyState.count--;
		System.out.println(NotReadyState.count);
	}

	@Override
	public void handleEvent(ZoneCheckEvent event) {
		NotReadyState.count++;
		System.out.println(NotReadyState.count);
	}

	@Override
	public void enter() {
		AlarmSystemContext.instance().showEnterPassword();
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub

	}

}
