package arcademis.server;

import arcademis.*;

/**
 * This exception denotes errors that happened during the remote object
 * initialization.
 */
public class ActivationException extends ArcademisException {

	/**
	 * Determines if a de-serialized file is compatible with this class.
	 * 
	 * Maintainers must change this value if and only if the new version of this
	 * class is not compatible with old versions. See Sun docs for <a
	 * href=http://java.sun.com/products/jdk/1.1/docs/guide
	 * /serialization/spec/version.doc.html> details. </a>
	 * 
	 */
	private static final long serialVersionUID = 2785609255622776147L;

	/**
	 * Empty constructor. Puts a standard message into the error stack.
	 */
	public ActivationException() {
		super("Error during remote objetc activation");
	}

	/**
	 * This constructor inserts the given message into the error queue.
	 * 
	 * @param msg
	 *            the message to be inserted into the queue.
	 */
	public ActivationException(String msg) {
		super(msg);
	}
}
