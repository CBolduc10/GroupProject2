package buttons;

import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import states.AlarmSystemContext;

/**
 * The ZoneCheckBox class is for when a zone checkbox of the GUI is selected or
 * unselected, as part of the AlarmSystem's checkbox hierarchy.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 */
public class ZoneCheckBox extends GUICheckBox implements EventHandler<ActionEvent> {

	/**
	 * Create the checkbox with the proper display
	 * 
	 * @param string the text to be put
	 */
	public ZoneCheckBox(String string) {
		super(string);
	}

	/**
	 * Upon receiving an ActionEvent, the GUI event is delivered to
	 * AlarmSystemContext for event handling. Checks if zone check or uncheck to
	 * determine if there's a ZoneUncheckEvent or ZoneCheckEvent
	 */
	@Override
	public void handle(ActionEvent event) {
		if (!this.isSelected()) {
			AlarmSystemContext.instance().handleEvent(ZoneUncheckEvent.instance());
		} else {
			AlarmSystemContext.instance().handleEvent(ZoneCheckEvent.instance());
		}
	}
}
