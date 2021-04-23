package states;

import events.TimerRanOutEvent;
import events.TimerTickedEvent;
import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;
import timer.Notifiable;
import timer.Timer;

public class WaitingState extends AlarmSystemState implements Notifiable {
	private static WaitingState instance;
	private Timer timer;
	private int count;

	/**
	 * Private for the singleton pattern
	 */
	private WaitingState() {
	}

	/**
	 * For singleton
	 * 
	 * @return the object
	 */
	public static WaitingState instance() {
		if (instance == null) {
			instance = new WaitingState();
		}
		return instance;
	}

	/**
	 * Process door open request
	 */
	@Override
	public void handleEvent(ZoneUncheckEvent event) {
		count--;
		NotReadyState.instance().setCount(count);
	}

	/**
	 * Process door open request
	 */
	@Override
	public void handleEvent(ZoneCheckEvent event) {
		count++;
		NotReadyState.instance().setCount(count);
	}

	/**
	 * Process clock tick event
	 */
	@Override
	public void handleEvent(TimerTickedEvent event) {
		AlarmSystemContext.instance().showTimeLeft(timer.getTimeValue());
	}

	/**
	 * Process the timer runs out event
	 */
	@Override
	public void handleEvent(TimerRanOutEvent event) {
		AlarmSystemContext.instance().showTimeLeft(0);
		if (count < 3) {
			AlarmSystemContext.instance().changeState(NotReadyState.instance());
		} else {
			AlarmSystemContext.instance().changeState(ArmedState.instance());
		}
	}

	/**
	 * Initializes the state Adds itself as a listener to managers Updates the
	 * displays
	 * 
	 */
	@Override
	public void enter() {
		count = NotReadyState.instance().getCount();
		timer = new Timer(this, 60);
		AlarmSystemContext.instance().showTimeLeft(timer.getTimeValue());
	}

	@Override
	public void leave() {
		timer.stop();
		timer = null;
		AlarmSystemContext.instance().showTimeLeft(0);
	}

}
