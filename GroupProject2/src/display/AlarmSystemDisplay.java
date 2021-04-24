package display;

public interface AlarmSystemDisplay {
	/**
	 * Displays the cook time remaining
	 * 
	 * @param time remaining time
	 */
	public void showTimeLeft(int time, String string);

	public void showReady();

	public void showNotReady();

	public void showAway();

	public void showStay();

	public void showBreached();

	public void showEnterPassword();

	public void showPassword(String passwordEntry);
}
