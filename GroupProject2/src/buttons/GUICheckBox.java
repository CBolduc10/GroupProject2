package buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;

/**
 * The abstract GUI CheckBox object. Helps to get rid of conditionals and ease
 * of future modification of the system.
 *
 * @author Ethan Nunn, Brian Le, Colin Bolduc, Daniel Renaud and Zachary
 *         Boling-Green
 */
public abstract class GUICheckBox extends CheckBox implements EventHandler<ActionEvent> {
	/**
	 * Create the checkbox with the proper text. Makes the button a listener to
	 * clicks on itself.
	 * 
	 * @param string the text
	 */
	public GUICheckBox(String string) {
		super(string);
		setOnAction(this);
	}
}
