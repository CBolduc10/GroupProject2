package states;

import events.EnterPasswordEvent;
import events.TimerRanOutEvent;
import events.TimerTickedEvent;
import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;
import password.Password;
import timer.Notifiable;
import timer.Timer;

/**
 * This class represents the warning state.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class WarningState extends AlarmSystemState implements Notifiable {
	private static WarningState instance;
	private Timer timer;

	/**
	 * Private constructor for the singleton pattern
	 */
	private WarningState() {
	}

	/**
	 * Provides an instance as it adheres to the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static WarningState instance() {
		if (instance == null) {
			instance = new WarningState();
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
	 * Process zone check event and increment count
	 * 
	 * @param event
	 */
	@Override
	public void handleEvent(ZoneCheckEvent event) {
		AlarmSystemContext.instance().increment();
	}

	/**
	 * Processes a clock tick event. Context indicates that the display should
	 * show time remaining.
	 * 
	 * @param event
	 */
	@Override
	public void handleEvent(TimerTickedEvent event) {
		AlarmSystemContext.instance().showTimeLeft(timer.getTimeValue(), null);
	}

	/**
	 * Processes the timer runs out event. Context indicates that the display
	 * should show time remaining as 0 and then switches to the alarm state.
	 * 
	 * @param event
	 */
	@Override
	public void handleEvent(TimerRanOutEvent event) {
		AlarmSystemContext.instance().showTimeLeft(0, null);
		AlarmSystemContext.instance().changeState(AlarmState.instance());
	}

	/**
	 * Initializes the state. Adds itself as a listener to managers. Updates the
	 * display to show the time remaining.
	 */
	@Override
	public void enter() {
		timer = new Timer(this, 15);
		AlarmSystemContext.instance().showTimeLeft(timer.getTimeValue(), null);
	}

	/**
	 * Upon leaving the state, the timer is removed from the list of observers
	 * and set back to null. The context indicates that the display should show
	 * the countdown as having reached 0 and the Password entry is cleared.
	 */
	@Override
	public void leave() {
		timer.stop();
		timer = null;
		AlarmSystemContext.instance().showTimeLeft(0, null);
		Password.instance().clear();
	}

}
