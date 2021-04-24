package buttons;

import events.EnterPasswordEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import states.AlarmSystemContext;

public class NumericButton extends GUIButton
		implements EventHandler<ActionEvent> {
	private int number;

	/**
	 * Create the button with the proper display
	 * 
	 * @param string the text to be put
	 */
	public NumericButton(String string) {
		super(string);
		number = Integer.parseInt(string);
	}

	@Override
	public void handle(ActionEvent event) {
		AlarmSystemContext.instance().handleEvent(EnterPasswordEvent.instance(),
				number);
	}
}