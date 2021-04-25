package states;

import events.EnterPasswordEvent;
import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;
import password.Password;

public class CancelState extends AlarmSystemState {
	private static CancelState instance;

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

	@Override
	public void handleEvent(ZoneUncheckEvent event) {
		AlarmSystemContext.instance().decrement();
		System.out.println(AlarmSystemContext.instance().getCount());
	}

	@Override
	public void handleEvent(ZoneCheckEvent event) {
		AlarmSystemContext.instance().decrement();
		System.out.println(AlarmSystemContext.instance().getCount());
	}

	@Override
	public void enter() {
		AlarmSystemContext.instance().showEnterPassword();
	}

	@Override
	public void leave() {
		Password.instance().clear();
	}

}
