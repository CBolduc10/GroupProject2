package buttons;

import events.StayEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import states.AlarmSystemContext;

public class StayButton extends GUIButton implements EventHandler<ActionEvent> {
	/**
	 * Create the button with the proper display
	 * 
	 * @param string the text to be put
	 */
	public StayButton(String string) {
		super(string);
	}

	@Override
	public void handle(ActionEvent event) {
		AlarmSystemContext.instance().handleEvent(StayEvent.instance());
	}
}
