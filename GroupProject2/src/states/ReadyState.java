package states;

import events.AwayEvent;
import events.StayEvent;
import events.ZoneUncheckEvent;

/**
 * This class represents the Ready state.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class ReadyState extends AlarmSystemState {
	private static ReadyState instance;

	/**
	 * Private constructor for the singleton pattern
	 */
	private ReadyState() {
	}

	/**
	 * Provides an instance as it adheres to the singleton pattern
	 * 
	 * @return the only instance
	 */
	public static ReadyState instance() {
		if (instance == null) {
			instance = new ReadyState();
		}
		return instance;
	}

	/**
	 * Processes the stay event, setting the context's armed state value to true
	 * and prompting a switch to the waiting state.
	 * 
	 * @param event
	 */
	@Override
	public void handleEvent(StayEvent event) {
		AlarmSystemContext.instance().setArmedStateValue(true);
		AlarmSystemContext.instance().changeState(WaitingState.instance());
	}

	/**
	 * Processes the away event, setting the context's armed state value to
	 * false and prompting a switch to the waiting state.
	 * 
	 * @param event
	 */
	@Override
	public void handleEvent(AwayEvent event) {
		AlarmSystemContext.instance().setArmedStateValue(false);
		AlarmSystemContext.instance().changeState(WaitingState.instance());
	}

	/**
	 * Process zone un-check event and decrement count. Prompts context to
	 * switch to Not Ready state.
	 * 
	 * @param event
	 */
	@Override
	public void handleEvent(ZoneUncheckEvent event) {
		AlarmSystemContext.instance().decrement();
		AlarmSystemContext.instance().changeState(NotReadyState.instance());
	}

	/**
	 * Upon entering the state, the context indicates to the display to show
	 * that the system is in the Ready state.
	 */
	@Override
	public void enter() {
		AlarmSystemContext.instance().showReady();
	}

	@Override
	public void leave() {

	}

}