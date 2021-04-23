package states;

public class ArmedState extends AlarmSystemState {
	private static ArmedState instance;
	private boolean armedStateValue;

	/**
	 * Private constructor for the singleton pattern
	 */
	private ArmedState() {
	}

	/**
	 * returns the instance
	 * 
	 * @return this object
	 */
	public static ArmedState instance() {
		if (instance == null) {
			instance = new ArmedState();
		}
		return instance;
	}

	@Override
	public void enter() {
		setArmedStateValue(ReadyState.instance().isArmedState());
		if (armedStateValue) {
			AlarmSystemContext.instance().showAway();
		} else {
			AlarmSystemContext.instance().showStay();
		}
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub

	}

	public boolean isArmedStateValue() {
		return armedStateValue;
	}

	public void setArmedStateValue(boolean armedStateValue) {
		this.armedStateValue = armedStateValue;
	}

}
