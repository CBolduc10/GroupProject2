package states;

import events.TimerRanOutEvent;
import events.TimerTickedEvent;
import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;
import timer.Notifiable;
import timer.Timer;

/**
 * This class represents the waiting state.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class WaitingState extends AlarmSystemState implements Notifiable {
	private static WaitingState instance;
	private Timer timer;
	private String away = "away";
	private String stay = "stay";

	/**
	 * Private for the singleton pattern
	 */
	private WaitingState() {
	}

	/**
	 * Provides an instance as it adheres to the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static WaitingState instance() {
		if (instance == null) {
			instance = new WaitingState();
		}
		return instance;
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
	 * Process zone un-check event and decrement count
	 * 
	 * @param event
	 */
	@Override
	public void handleEvent(ZoneCheckEvent event) {
		AlarmSystemContext.instance().increment();
	}

	/**
	 * Processes a clock tick event. The context prompts the display to update
	 * the time left and indicate whether stay or away mode is in operation
	 * 
	 * @param event
	 */
	@Override
	public void handleEvent(TimerTickedEvent event) {
		if (AlarmSystemContext.instance().getArmedStateValue()) {
			AlarmSystemContext.instance().showTimeLeft(timer.getTimeValue(),
					stay);
		} else {
			AlarmSystemContext.instance().showTimeLeft(timer.getTimeValue(),
					away);
		}
	}

	/**
	 * Process the timer runs out event. The value of context's count indicates
	 * whether to switch to the Not Ready or Ready states
	 * 
	 * @param event
	 */
	@Override
	public void handleEvent(TimerRanOutEvent event) {
		AlarmSystemContext.instance().showTimeLeft(0, null);
		if (AlarmSystemContext.instance().getCount() < 3) {
			AlarmSystemContext.instance().changeState(NotReadyState.instance());
		} else {
			AlarmSystemContext.instance().changeState(ArmedState.instance());
		}
	}

	/**
	 * Initializes the state. Adds itself as a listener to managers. Updates the
	 * display according to the armed state value in context.
	 */
	@Override
	public void enter() {
		timer = new Timer(this, 10);
		if (AlarmSystemContext.instance().getArmedStateValue()) {
			AlarmSystemContext.instance().showTimeLeft(timer.getTimeValue(),
					stay);
		} else {
			AlarmSystemContext.instance().showTimeLeft(timer.getTimeValue(),
					away);
		}
	}

	/**
	 * Upon leaving the state, the timer is removed from the list of observers
	 * and set back to null. The context indicates that the display should show
	 * the countdown as having reached 0.
	 */
	@Override
	public void leave() {
		timer.stop();
		timer = null;
		AlarmSystemContext.instance().showTimeLeft(0, null);
	}

}
