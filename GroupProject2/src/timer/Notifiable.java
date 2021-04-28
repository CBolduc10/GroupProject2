package timer;

import events.TimerRanOutEvent;
import events.TimerTickedEvent;

/**
 * An entity that can be notified of timing events
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public interface Notifiable {

	/**
	 * Process timer ticks
	 */
	public void handleEvent(TimerTickedEvent event);

	/**
	 * Process timer runs out event
	 */
	public void handleEvent(TimerRanOutEvent event);

}
