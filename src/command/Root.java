package command;

/**
 * The Class Root. Root of the ast
 */
public class Root extends Container {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6350948888560121347L;

	/**
	 * Instantiates a new root.
	 */
	public Root() {
		super(0, "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Container#toString()
	 */
	@Override
	public String toString() {
		return "Root []";
	}
}
