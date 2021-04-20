package buttons;

import events.ZoneCheckEvent;
import events.ZoneUncheckEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import states.AlarmSystemContext;

public class ZoneTwoCheckBox extends GUICheckBox
		implements EventHandler<ActionEvent> {

	public ZoneTwoCheckBox(String string) {
		super(string);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(ActionEvent event) {
		if (this.isSelected()) {
			AlarmSystemContext.instance()
					.handleEvent(ZoneUncheckEvent.instance());
		} else {
			AlarmSystemContext.instance()
					.handleEvent(ZoneCheckEvent.instance());
		}
	}

}
