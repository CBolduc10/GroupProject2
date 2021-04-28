package display;

/**
 * Specifies what the AlarmSystem display system should do. Note that the
 * implementation has a lot of freedom to choose its display.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 */
public interface AlarmSystemDisplay {

	/**
	 * Displays the time remaining in states utilizing a timer (WaitingState and
	 * WarningState).
	 * 
	 * @param time   remaining time
	 * @param string string modification for the display
	 */
	public void showTimeLeft(int time, String string);

	/**
	 * Indicate that the system is ready.
	 */
	public void showReady();

	/**
	 * Indicate that the system is not ready.
	 */
	public void showNotReady();

	/**
	 * Indicate that the system is operating in away mode.
	 */
	public void showAway();

	/**
	 * Indicate that the system is in operating in stay mode.
	 */
	public void showStay();

	/**
	 * Indicate that the system has been breached (alarm triggered).
	 */
	public void showBreached();

	/**
	 * Indicate that the user should enter a password
	 */
	public void showEnterPassword();

	/**
	 * Displays the password as per entry
	 * 
	 * @param passwordEntry a string of the password
	 */
	public void showPassword(String passwordEntry);
}
