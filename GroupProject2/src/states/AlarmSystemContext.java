package states;

import display.AlarmSystemDisplay;
import events.AwayEvent;
import events.CancelEvent;
import events.EnterPasswordEvent;
import events.MotionDetectionEvent;
import events.StayEvent;
import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;

/**
 * The context is an observer for the clock and stores the context info for each
 * of the states. It also tracks the count utilized for zone check and un-check
 * event handling and the armed state value for the stay or away modes.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class AlarmSystemContext {
	private AlarmSystemDisplay display;
	private AlarmSystemState currentState;
	private static AlarmSystemContext instance;
	private int count;
	private boolean armedStateValue;

	/**
	 * Makes the context a singleton, sets the count to 0, and the state to the
	 * Not Ready state.
	 */
	private AlarmSystemContext() {
		instance = this;
		count = 0;
		currentState = NotReadyState.instance();
	}

	/**
	 * Return the only instance of context
	 * 
	 * @return the only instance
	 */
	public static AlarmSystemContext instance() {
		if (instance == null) {
			instance = new AlarmSystemContext();
		}
		return instance;
	}

	/**
	 * The display could change. So we have to get its reference.
	 * 
	 * @param display The current display object
	 */
	public void setDisplay(AlarmSystemDisplay display) {
		this.display = display;
	}

	/**
	 * Lets Not Ready state be the starting state adds the object as an
	 * observable for clock.
	 */
	public void initialize() {
		instance.changeState(NotReadyState.instance());
	}

	/**
	 * Called from the states to change the current state.
	 * 
	 * @param nextState the next state
	 */
	public void changeState(AlarmSystemState nextState) {
		currentState.leave();
		currentState = nextState;
		currentState.enter();
	}

	/**
	 * Method to return the count for zone checking
	 * 
	 * @return count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Method to increment count
	 */
	public void increment() {
		count++;
	}

	/**
	 * Method to decrement count
	 */
	public void decrement() {
		count--;
	}

	/**
	 * Method to get the armed state value for stay or away
	 * 
	 * @return true or false (stay/away)
	 */
	public boolean getArmedStateValue() {
		return armedStateValue;
	}

	/**
	 * Method to set the armed state value for stay or away
	 * 
	 * @param armedStateValue true or false (stay/away)
	 */
	public void setArmedStateValue(boolean armedStateValue) {
		this.armedStateValue = armedStateValue;
	}

	/**
	 * Method to process away request/event
	 * 
	 * @param event
	 */
	public void handleEvent(AwayEvent event) {
		currentState.handleEvent(event);
	}

	/**
	 * Method to process stay request/event
	 * 
	 * @param event
	 */
	public void handleEvent(StayEvent event) {
		currentState.handleEvent(event);
	}

	/**
	 * Method to process cancel request/event
	 * 
	 * @param event
	 */
	public void handleEvent(CancelEvent event) {
		currentState.handleEvent(event);
	}

	/**
	 * Method to process enter password request/event and corresponding numeric
	 * value for the event.
	 * 
	 * @param event
	 * @param number the numeric value corresponding to the origin of the event
	 */
	public void handleEvent(EnterPasswordEvent event, int number) {
		currentState.handleEvent(event, number);
	}

	/**
	 * Method to process motion detection request/event
	 * 
	 * @param event
	 */
	public void handleEvent(MotionDetectionEvent event) {
		currentState.handleEvent(event);
	}

	/**
	 * Method to process zone check request/event
	 * 
	 * @param event
	 */
	public void handleEvent(ZoneCheckEvent event) {
		currentState.handleEvent(event);
	}

	/**
	 * Method to process zone un-check request/event
	 * 
	 * @param event
	 */
	public void handleEvent(ZoneUncheckEvent event) {
		currentState.handleEvent(event);
	}

	/**
	 * This invokes the right method of the display, in this case to show the
	 * time remaining. This helps protect the states from changes to the way the
	 * system utilizes the state changes.
	 * 
	 * @param time   time left
	 * @param string value for display
	 */
	public void showTimeLeft(int time, String value) {
		display.showTimeLeft(time, value);
	}

	/**
	 * This invokes the right method of the display, in this case to show the
	 * system is in Ready state. This helps protect the states from changes to
	 * the way the system utilizes the state changes.
	 */
	public void showReady() {
		display.showReady();
	}

	/**
	 * This invokes the right method of the display, in this case to show the
	 * system is in Not Ready state. This helps protect the states from changes
	 * to the way the system utilizes the state changes.
	 */
	public void showNotReady() {
		display.showNotReady();
	}

	/**
	 * This invokes the right method of the display, in this case to show the
	 * system is in Armed state in away mode. This helps protect the states from
	 * changes to the way the system utilizes the state changes.
	 */
	public void showAway() {
		display.showAway();
	}

	/**
	 * This invokes the right method of the display, in this case to show the
	 * system is in Armed state in stay mode. This helps protect the states from
	 * changes to the way the system utilizes the state changes.
	 */
	public void showStay() {
		display.showStay();
	}

	/**
	 * This invokes the right method of the display, in this case to show the
	 * system is in Alarm state. This helps protect the states from changes to
	 * the way the system utilizes the state changes.
	 */
	public void showBreached() {
		display.showBreached();
	}

	/**
	 * This invokes the right method of the display, in this case to prompt for
	 * a password as the system is in Cancel state. This helps protect the
	 * states from changes to the way the system utilizes the state changes.
	 */
	public void showEnterPassword() {
		display.showEnterPassword();

	}

	/**
	 * This invokes the right method of the display, in this case to display the
	 * active password entry while in the Cancel state. This helps protect the
	 * states from changes to the way the system utilizes the state changes.
	 * 
	 * @param passwordEntry the password as entered
	 */
	public void showPassword(String passwordEntry) {
		display.showPassword(passwordEntry);

	}

}