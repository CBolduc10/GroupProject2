package states;

import events.EnterPasswordEvent;
import events.TimerRanOutEvent;
import events.TimerTickedEvent;
import password.Password;
import timer.Notifiable;
import timer.Timer;

public class WarningState extends AlarmSystemState implements Notifiable {
	private static WarningState instance;
	private Timer timer;
	private Password password = new Password();

	/**
	 * Private for the singleton pattern
	 */
	private WarningState() {
	}

	/**
	 * For singleton
	 * 
	 * @return the object
	 */
	public static WarningState instance() {
		if (instance == null) {
			instance = new WarningState();
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

	/**
	 * Process clock tick event
	 */
	@Override
	public void handleEvent(TimerTickedEvent event) {
		AlarmSystemContext.instance().showTimeLeft(timer.getTimeValue(), null);
	}

	/**
	 * Process the timer runs out event
	 */
	@Override
	public void handleEvent(TimerRanOutEvent event) {
		AlarmSystemContext.instance().changeState(AlarmState.instance());
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		timer = new Timer(this, 15);
		AlarmSystemContext.instance().showTimeLeft(timer.getTimeValue(), null);
	}

	@Override
	public void leave() {
		timer.stop();
		timer = null;
		AlarmSystemContext.instance().showTimeLeft(0, null);
	}

}
