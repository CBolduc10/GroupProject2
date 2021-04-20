package buttons;

import events.MotionDetectionEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import states.AlarmSystemContext;

public class MotionDetectorButton extends GUIButton
		implements EventHandler<ActionEvent> {

	public MotionDetectorButton(String string) {
		super(string);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(ActionEvent event) {
		AlarmSystemContext.instance()
				.handleEvent(MotionDetectionEvent.instance());
	}
}
