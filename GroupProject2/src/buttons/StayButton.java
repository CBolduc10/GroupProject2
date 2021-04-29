package buttons;

import events.StayEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import states.AlarmSystemContext;

/**
 * The StayButton class is for when the Stay button of the GUI is clicked, as
 * part of the AlarmSystem's button hierarchy.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 */
public class StayButton extends GUIButton implements EventHandler<ActionEvent> {

	/**
	 * Create the button with the proper display
	 * 
	 * @param string the text to be put
	 */
	public StayButton(String string) {
		super(string);
	}

	/**
	 * Upon receiving an ActionEvent, the GUI event is delivered to
	 * AlarmSystemContext for event handling.
	 */
	@Override
	public void handle(ActionEvent event) {
		AlarmSystemContext.instance().handleEvent(StayEvent.instance());
	}
}
