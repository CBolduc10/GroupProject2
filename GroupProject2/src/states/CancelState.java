package states;

public class CancelState extends AlarmSystemState {
	private static CancelState instance;

	/**
	 * Private constructor for the singleton pattern
	 */
	private CancelState() {
	}

	/**
	 * returns the instance
	 * 
	 * @return this object
	 */
	public static CancelState instance() {
		if (instance == null) {
			instance = new CancelState();
		}
		return instance;
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub

	}

}
