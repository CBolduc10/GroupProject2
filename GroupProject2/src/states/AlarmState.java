package states;

import events.EnterPasswordEvent;
import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;
import password.Password;

public class AlarmState extends AlarmSystemState {
	private static AlarmState instance;

	private AlarmState() {
	}

	public static AlarmState instance() {
		if (instance == null) {
			instance = new AlarmState();
		}
		return instance;
	}

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

	@Override
	public void handleEvent(ZoneUncheckEvent event) {
		AlarmSystemContext.instance().decrement();
		System.out.println(AlarmSystemContext.instance().getCount());
	}

	@Override
	public void handleEvent(ZoneCheckEvent event) {
		AlarmSystemContext.instance().increment();
		System.out.println(AlarmSystemContext.instance().getCount());
	}

	@Override
	public void enter() {
		AlarmSystemContext.instance().showBreached();
	}

	@Override
	public void leave() {
		Password.instance().clear();
	}

}
