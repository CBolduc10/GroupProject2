package states;

import events.EnterPasswordEvent;
import events.TimerRanOutEvent;
import events.TimerTickedEvent;
import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;
import password.Password;
import timer.Notifiable;
import timer.Timer;

public class WarningState extends AlarmSystemState implements Notifiable {
	private static WarningState instance;
	private Timer timer;

	/**
	 * Private constructor for the singleton pattern
	 */
	private WarningState() {
	}

	/**
	 * returns the instance
	 * 
	 * @return this object
	 */
	public static WarningState instance() {
		if (instance == null) {
			instance = new WarningState();
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
		AlarmSystemContext.instance().showTimeLeft(0, null);
		AlarmSystemContext.instance().changeState(AlarmState.instance());
	}

	@Override
	public void enter() {
		timer = new Timer(this, 20);
		AlarmSystemContext.instance().showTimeLeft(timer.getTimeValue(), null);

	}

	@Override
	public void leave() {
		timer.stop();
		timer = null;
		AlarmSystemContext.instance().showTimeLeft(0, null);
		Password.instance().clear();
	}

}
