package application;

import display.AlarmSystemDisplay;
import display.GUIDisplay;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import states.AlarmSystemContext;
import timer.Clock;

/**
 * Main sets up the stage for the program's GUI and launches the program.
 */
public class Main extends Application {

	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
