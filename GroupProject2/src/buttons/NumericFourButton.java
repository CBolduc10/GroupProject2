package buttons;

import events.EnterPasswordEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import states.AlarmSystemContext;

public class NumericFourButton extends GUIButton
		implements EventHandler<ActionEvent> {
	/**
	 * Create the button with the proper display
	 * 
	 * @param string the text to be put
	 */
	public NumericFourButton(String string) {
		super(string);
	}

	@Override
	public void handle(ActionEvent event) {
		AlarmSystemContext.instance()
				.handleEvent(EnterPasswordEvent.instance());
	}
}