package states;

import events.AwayEvent;
import events.CancelEvent;
import events.EnterPasswordEvent;
import events.MotionDetectionEvent;
import events.StayEvent;
import events.TimerRanOutEvent;
import events.TimerTickedEvent;
import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;

/**
 * Super class for all states
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 */
public abstract class AlarmSystemState {

	/**
	 * Initializes the state
	 */
	public abstract void enter();

	/**
	 * Performs any necessary clean up while leaving the state
	 */
	public abstract void leave();

	/**
	 * Specifies the actions to be taken when the Away button is pressed.
	 */
	public void handleEvent(AwayEvent event) {

	}

	/**
	 * Specifies the actions to be taken when the Stay button is pressed.
	 */
	public void handleEvent(StayEvent event) {

	}

	/**
	 * Process cancel request
	 */
	public void handleEvent(CancelEvent event) {

	}

	/**
	 * Process zone check request
	 */
	public void handleEvent(ZoneCheckEvent event) {

	}

	/**
	 * Process zone uncheck request
	 */
	public void handleEvent(ZoneUncheckEvent event) {

	}

	/**
	 * Process enter password request
	 * 
	 * @param number
	 */
	public void handleEvent(EnterPasswordEvent event, int number) {

	}

	/**
	 * Process motion detected
	 */
	public void handleEvent(MotionDetectionEvent event) {

	}

	/**
	 * Process clock tick Generates the timer runs out event
	 */
	public void handleEvent(TimerTickedEvent event) {

	}

	/**
	 * Process clock ticks Generates the timer runs out event
	 */
	public void handleEvent(TimerRanOutEvent event) {

	}
}
