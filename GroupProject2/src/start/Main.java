package start;

import display.GUIDisplay;

/**
 * Test modification 1
 */

import display.AlarmSystemDisplay;
import javafx.application.Application;
import states.AlarmSystemContext;
import timer.Clock;

public class Main {
	// Ethan
	// Zach
	public static void main(String[] args) {
		Clock.instance();
		new Thread() {
			@Override
			public void run() {
				Application.launch(GUIDisplay.class, null);
			}
		}.start();
		try {
			while (GUIDisplay.getInstance() == null) {
				Thread.sleep(1000);
			}
		} catch (InterruptedException ie) {
		}
		AlarmSystemDisplay display = GUIDisplay.getInstance();
		AlarmSystemContext.instance().setDisplay(display);
	}
}
