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
	private boolean armedStateValue;
	private String away = "away";
	private String stay = "stay";
	// private int count;

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

	@Override
	public void handleEvent(ZoneUncheckEvent event) {
		NotReadyState.count--;
		// count--;
		// NotReadyState.instance().setCount(count);
		System.out.println(NotReadyState.count);
	}

	@Override
	public void handleEvent(ZoneCheckEvent event) {
		NotReadyState.count++;
		// count++;
		// NotReadyState.instance().setCount(count);
		System.out.println(NotReadyState.count);
	}

	/**
	 * Process clock tick event
	 */
	@Override
	public void handleEvent(TimerTickedEvent event) {
		if (ReadyState.armedStateValue) {
			AlarmSystemContext.instance().showTimeLeft(timer.getTimeValue(),
					stay);
		} else {
			AlarmSystemContext.instance().showTimeLeft(timer.getTimeValue(),
					away);
		}
	}

	/**
	 * Process the timer runs out event
	 */
	@Override
	public void handleEvent(TimerRanOutEvent event) {
		AlarmSystemContext.instance().showTimeLeft(0, null);
		if (NotReadyState.count < 3) {
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
		armedStateValue = ReadyState.armedStateValue;
		timer = new Timer(this, 5);
		if (armedStateValue) {
			AlarmSystemContext.instance().showTimeLeft(timer.getTimeValue(),
					stay);
		} else {
			AlarmSystemContext.instance().showTimeLeft(timer.getTimeValue(),
					away);
		}
	}

	@Override
	public void leave() {
		timer.stop();
		timer = null;
		AlarmSystemContext.instance().showTimeLeft(0, null);
	}

}
