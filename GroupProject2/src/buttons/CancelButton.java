package buttons;

import events.CancelEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import states.AlarmSystemContext;

public class CancelButton extends GUIButton
		implements EventHandler<ActionEvent> {
	/**
	 * Create the button with the proper display
	 * 
	 * @param string the text to be put
	 */
	public CancelButton(String string) {
		super(string);
	}

	@Override
	public void handle(ActionEvent event) {
		AlarmSystemContext.instance().handleEvent(CancelEvent.instance());
	}
}
