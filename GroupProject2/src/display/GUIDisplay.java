package display;

import buttons.AwayButton;
import buttons.CancelButton;
import buttons.GUIButton;
import buttons.GUICheckBox;
import buttons.MotionDetectorButton;
import buttons.NumericButton;
import buttons.StayButton;
import buttons.ZoneCheckBox;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import states.AlarmSystemContext;

/**
 * GUI to implement the AlarmSystemDisplay interface.
 * 
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 *
 */
public class GUIDisplay extends Application implements AlarmSystemDisplay {
	private GUIButton awayButton;
	private GUIButton stayButton;
	private GUIButton cancelButton;
	private GUIButton motionButton;
	private GUIButton numeric1Button;
	private GUIButton numeric2Button;
	private GUIButton numeric3Button;
	private GUIButton numeric4Button;
	private GUIButton numeric5Button;
	private GUIButton numeric6Button;
	private GUIButton numeric7Button;
	private GUIButton numeric8Button;
	private GUIButton numeric9Button;
	private GUIButton numeric0Button;
	private GUICheckBox zone1Check;
	private GUICheckBox zone2Check;
	private GUICheckBox zone3Check;
	private TextField message = new TextField("Not Ready");
	private static AlarmSystemDisplay display;
	private AlarmSystemContext AlarmSystemContext;

	public static AlarmSystemDisplay getInstance() {
		return display;
	}

	/**
	 * Sets up the interface
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		AlarmSystemContext = AlarmSystemContext.instance();
		AlarmSystemContext.setDisplay(this);
		display = this;

		/**
		 * Creates two containers that will help to format the GUI Display
		 */
		VBox vbox = new VBox();
		HBox hbox = new HBox();

		/**
		 * Creates all buttons / check boxes for display.
		 */
		stayButton = new StayButton("Stay");
		awayButton = new AwayButton("Away");
		cancelButton = new CancelButton("Cancel");
		motionButton = new MotionDetectorButton("Motion Detector");
		numeric1Button = new NumericButton("1");
		numeric2Button = new NumericButton("2");
		numeric3Button = new NumericButton("3");
		numeric4Button = new NumericButton("4");
		numeric5Button = new NumericButton("5");
		numeric6Button = new NumericButton("6");
		numeric7Button = new NumericButton("7");
		numeric8Button = new NumericButton("8");
		numeric9Button = new NumericButton("9");
		numeric0Button = new NumericButton("0");
		zone1Check = new ZoneCheckBox("Zone 1");
		zone2Check = new ZoneCheckBox("Zone 2");
		zone3Check = new ZoneCheckBox("Zone 3");

		/**
		 * Creates three GridPane objects, one for keypad, one for the textField, and
		 * the third for the zones / control buttons.
		 */

		GridPane controlPane = new GridPane();
		GridPane keypadPane = new GridPane();
		GridPane textFieldPane = new GridPane();

		/**
		 * Formats the controlpane.
		 */
		controlPane.setHgap(10);
		controlPane.setVgap(5);
		controlPane.setPadding(new Insets(5, 5, 5, 5));

		/**
		 * Formats the textField object and textFieldPane.
		 */
		message.setMinWidth(200);
		message.setMinHeight(80);
		message.setAlignment(Pos.TOP_LEFT);
		textFieldPane.setPadding(new Insets(5, 5, 0, 5));

		/**
		 * Format the keyboardPane
		 */
		keypadPane.setPadding(new Insets(5, 5, 0, 5));

		/**
		 * add all keypad buttons and format them into a standard layout in their pane.
		 */
		keypadPane.add(numeric1Button, 0, 0);
		keypadPane.add(numeric2Button, 1, 0);
		keypadPane.add(numeric3Button, 2, 0);
		keypadPane.add(numeric4Button, 0, 1);
		keypadPane.add(numeric5Button, 1, 1);
		keypadPane.add(numeric6Button, 2, 1);
		keypadPane.add(numeric7Button, 0, 2);
		keypadPane.add(numeric8Button, 1, 2);
		keypadPane.add(numeric9Button, 2, 2);
		keypadPane.add(numeric0Button, 1, 3);

		/**
		 * Add textField object to textFieldPane
		 */
		textFieldPane.add(message, 0, 0);

		/**
		 * Add all buttons to controlPane
		 */
		controlPane.add(zone1Check, 0, 4);
		controlPane.add(zone2Check, 1, 4);
		controlPane.add(zone3Check, 2, 4);
		controlPane.add(motionButton, 3, 4);
		controlPane.add(stayButton, 0, 7);
		controlPane.add(awayButton, 1, 7);
		controlPane.add(cancelButton, 2, 7);

		/**
		 * Sets hbox to include two columns, with keypadPane on the left, and
		 * textFieldPane on the right. Then, sets vbox to include the hbox on the top,
		 * and controlPane on the bottom. This gives us a three sector layout for the
		 * security GUI.
		 */
		hbox.getChildren().addAll(keypadPane, textFieldPane);
		vbox.getChildren().addAll(hbox, controlPane);
		Scene scene = new Scene(vbox);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Alarm System PRE-ALPHA 1.0");
		try {
			while (AlarmSystemContext == null) {
				Thread.sleep(1000);
			}
		} catch (Exception e) {

		}
		primaryStage.show();
		primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent window) {
				System.exit(0);
			}
		});
	}

	/**
	 * Updates the message TextField to display "Ready"
	 */
	@Override
	public void showReady() {
		message.setText("Ready");
	}

	/**
	 * Updates the message TextField to display "Not Ready"
	 */
	@Override
	public void showNotReady() {
		message.setText("Not Ready");
	}

	/**
	 * Updates the message TextField to display "Stay Alarmed"
	 */
	@Override
	public void showStay() {
		message.setText("Stay Alarmed");
	}

	/**
	 * Updates the message TextField to display "Away Alarmed"
	 */
	@Override
	public void showAway() {
		message.setText("Away Alarmed");
	}

	/**
	 * Updates the message TextField to display time remaining and corresponding
	 * text.
	 */
	@Override
	public void showTimeLeft(int value, String string) {
		if (string == null) {
			message.setText(value + " seconds");
		} else {
			message.setText(value + " seconds for " + string);
		}
	}

	/**
	 * Updates the message TextField to display "Security Breached"
	 */
	public void showBreached() {
		message.setText("Security Breached");
	}

	/**
	 * Updates the message TextField to display "Enter Password to Cancel"
	 */
	@Override
	public void showEnterPassword() {
		message.setText("Enter Password to Cancel");
	}

	/**
	 * Updates the message TextField to display the password as it's entered.
	 */
	@Override
	public void showPassword(String passwordEntry) {
		message.setText(passwordEntry);
	}
}