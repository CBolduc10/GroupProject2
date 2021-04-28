package events;

/**
 * This class represents a clock tick. The object contains the amount of time
 * left in the timer.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class TimerTickedEvent extends AlarmSystemEvent {
	private int timeLeft;

	/**
	 * Stores the amount of time left in the Timer.
	 * 
	 * @param value the amount of time left
	 */
	public TimerTickedEvent(int value) {
		this.timeLeft = value;
	}

	/**
	 * Used to display the remaining time.
	 */
	public int getTimeLeft() {
		return timeLeft;
	}
}
