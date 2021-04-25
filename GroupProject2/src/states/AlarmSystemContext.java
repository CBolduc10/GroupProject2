package states;

import display.AlarmSystemDisplay;
import events.AwayEvent;
import events.CancelEvent;
import events.EnterPasswordEvent;
import events.MotionDetectionEvent;
import events.StayEvent;
import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;

public class AlarmSystemContext {
	private AlarmSystemDisplay display;
	private AlarmSystemState currentState;
	private static AlarmSystemContext instance;
	private int count;
	private boolean armedStateValue;

	/**
	 * Make it a singleton
	 */
	private AlarmSystemContext() {
		instance = this;
		count = 0;
		currentState = NotReadyState.instance();
	}

	/**
	 * Return the instance
	 * 
	 * @return the object
	 */
	public static AlarmSystemContext instance() {
		if (instance == null) {
			instance = new AlarmSystemContext();
		}
		return instance;
	}

	/**
	 * The display could change. So we have to get its refrence.
	 * 
	 * @param display The current display object
	 */
	public void setDisplay(AlarmSystemDisplay display) {
		this.display = display;
	}

	/**
	 * Lets door closed state be the starting state adds the object as an
	 * observable for clock
	 */
	public void initialize() {
		instance.changeState(NotReadyState.instance());
	}

	/**
	 * Called from the states to change the current state
	 * 
	 * @param nextState the next state
	 */
	public void changeState(AlarmSystemState nextState) {
		currentState.leave();
		currentState = nextState;
		currentState.enter();
	}

	public int getCount() {
		return count;
	}

	public void increment() {
		count++;
	}

	public void decrement() {
		count--;
	}

	public boolean getArmedStateValue() {
		return armedStateValue;
	}

	public void setArmedStateValue(boolean armedStateValue) {
		this.armedStateValue = armedStateValue;
	}

	public void showTimeLeft(int time, String value) {
		display.showTimeLeft(time, value);
	}

	public void handleEvent(AwayEvent event) {
		currentState.handleEvent(event);
	}

	public void handleEvent(StayEvent event) {
		currentState.handleEvent(event);
	}

	public void handleEvent(CancelEvent event) {
		currentState.handleEvent(event);
	}

	public void handleEvent(EnterPasswordEvent event, int number) {
		currentState.handleEvent(event, number);
	}

	public void handleEvent(MotionDetectionEvent event) {
		currentState.handleEvent(event);
	}

	public void handleEvent(ZoneCheckEvent event) {
		currentState.handleEvent(event);
	}

	public void handleEvent(ZoneUncheckEvent event) {
		currentState.handleEvent(event);
	}

	public void showReady() {
		display.showReady();
	}

	public void showNotReady() {
		display.showNotReady();
	}

	public void showAway() {
		display.showAway();
	}

	public void showStay() {
		display.showStay();
	}

	public void showBreached() {
		display.showBreached();
	}

	public void showEnterPassword() {
		display.showEnterPassword();

	}

	public void showPassword(String passwordEntry) {
		display.showPassword(passwordEntry);

	}

}