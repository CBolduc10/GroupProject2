package buttons;

import events.EnterPasswordEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import states.AlarmSystemContext;

/**
 * The NumericButton class is for when a numeric button of the GUI is clicked,
 * as part of the AlarmSystem's button hierarchy. It converts the GUI button
 * string into a number that represents each specific button's numeric value.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 */
public class NumericButton extends GUIButton implements EventHandler<ActionEvent> {
	private int number;

	/**
	 * Create the button with the proper display. Parses the string as a number to
	 * be passed with the event.
	 * 
	 * @param string the text to be put
	 */
	public NumericButton(String string) {
		super(string);
		number = Integer.parseInt(string);
	}

	/**
	 * Upon receiving an ActionEvent, the GUI event is delivered to
	 * AlarmSystemContext for event handling as well as a corresponding numeric
	 * value.
	 */
	@Override
	public void handle(ActionEvent event) {
		AlarmSystemContext.instance().handleEvent(EnterPasswordEvent.instance(), number);
	}
}