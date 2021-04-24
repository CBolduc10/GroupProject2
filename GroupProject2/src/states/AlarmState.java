package states;

import events.EnterPasswordEvent;
import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;
import password.Password;

public class AlarmState extends AlarmSystemState {
	private static AlarmState instance;
	private Password password = new Password();

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
		if (password.entry(number)) {
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
		AlarmSystemContext.instance().showBreached();
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub

	}

}
