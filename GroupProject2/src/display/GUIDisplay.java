package display;

import buttons.GUIButton;
import buttons.GUICheckBox;
import buttons.StayButton;
import buttons.AwayButton;
import buttons.CancelButton;
import buttons.NumericOneButton;
import buttons.NumericTwoButton;
import buttons.NumericThreeButton;
import buttons.NumericFourButton;
import buttons.NumericFiveButton;
import buttons.NumericSixButton;
import buttons.NumericSevenButton;
import buttons.NumericEightButton;
import buttons.NumericNineButton;
import buttons.NumericZeroButton;
import buttons.ZoneOneCheckBox;
import buttons.ZoneTwoCheckBox;
import buttons.ZoneThreeCheckBox;

/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import states.AlarmSystemContext;

/**
 * GUI to implement the MicrowaveDisplay interface A pretty elementary interface
 *
 */
public class GUIDisplay extends Application implements AlarmSystemDisplay {
	private GUIButton awayButton;
	private GUIButton stayButton;
	private GUIButton cancelButton;
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
    private Text readyState = new Text("Not Ready");
    private Text alarmState = new Text("Not Armed");
    private Text timerValue = new Text("         ");
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
        stayButton = new StayButton("Stay");
        awayButton = new AwayButton("Away");
        cancelButton = new CancelButton("Cancel");
        numeric1Button = new NumericOneButton("1");
        numeric2Button = new NumericTwoButton("2");
        numeric3Button = new NumericThreeButton("3");
        numeric4Button = new NumericFourButton("4");
        numeric5Button = new NumericFiveButton("5");
        numeric6Button = new NumericSixButton("6");
        numeric7Button = new NumericSevenButton("7");
        numeric8Button = new NumericEightButton("8");
        numeric9Button = new NumericNineButton("9");
        numeric0Button = new NumericZeroButton("0");
        zone1Check = new ZoneOneCheckBox("Zone 1");
        zone2Check = new ZoneTwoCheckBox("Zone 2");
        zone3Check = new ZoneThreeCheckBox("Zone 3");

        GridPane pane = new GridPane   ();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(10, 10, 10, 10));
        /*
        pane.add(doorStatus, 0, 0);
        pane.add(lightStatus, 1, 0);
        pane.add(timerValue, 2, 0);
        pane.add(cookingStatus, 3, 0);
        pane.add(doorCloser, 4, 0);
        pane.add(doorOpener, 5, 0);
        pane.add(cookButton, 6, 0);
        */
        //Add Keypad
        pane.add(numeric1Button, 0, 0);
        pane.add(numeric2Button, 1, 0);
        pane.add(numeric3Button, 2, 0);
        pane.add(numeric4Button, 0, 1);
        pane.add(numeric5Button, 1, 1);
        pane.add(numeric6Button, 2, 1);
        pane.add(numeric7Button, 0, 2);
        pane.add(numeric8Button, 1, 2);
        pane.add(numeric9Button, 2, 2);
        pane.add(numeric0Button, 1, 3);
        //Add Check Boxes
        pane.add(zone1Check, 0, 4);
        pane.add(zone2Check, 1, 4);
        pane.add(zone3Check, 2, 4);
        //Add State Identifier
        pane.add(readyState, 0, 5);
        pane.add(alarmState, 0, 6);
        pane.add(stayButton, 0, 7);
        pane.add(awayButton, 1, 7);
        pane.add(cancelButton, 2, 7);
        pane.add(timerValue, 1, 8);
        showTimeLeft(0);
        Scene scene = new Scene(pane);
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

    //Show ready
    @Override
    public void showReady() {
    	readyState.setText("Ready");
    }
    
    //Show not ready
    @Override
    public void showNotReady() {
    	readyState.setText("Not Ready");
    }
    
  //Show stay
    @Override
    public void showStay() {
    	readyState.setText("Stay Alarmed");
    }
    
  //Show away
    @Override
    public void showAway() {
    	readyState.setText("Away Alarmed");
    }
    
    @Override
    public void showTimeLeft(int value) {
        timerValue.setText(" " + value);
    }

}