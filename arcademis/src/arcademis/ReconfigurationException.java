package arcademis;

/**
 * This exception is thrown whenever an attempt to reconfigure the ORB is
 * performed, but the ORB is closed for reconfigurations. Generally, after
 * determining the set of factories that composes the ORB, the application close
 * it for further reconfigurations, so that it does not need to care about
 * dynamic changes in the middleware behavior.
 */
public class ReconfigurationException extends ArcademisException {

	/**
	 * Determines if a de-serialized file is compatible with this class.
	 * 
	 * Maintainers must change this value if and only if the new version of this
	 * class is not compatible with old versions. See Sun docs for <a
	 * href=http://java.sun.com/products/jdk/1.1/docs/guide
	 * /serialization/spec/version.doc.html> details. </a>
	 * 
	 */
	private static final long serialVersionUID = 2399374155626753947L;

	/**
	 * Empty constructor. Puts a standard message into the error stack.
	 */
	public ReconfigurationException() {
		super("Attempt to reconfigure ORB close of reconfiguration");
	}

	/**
	 * This constructor inserts the given message into the error queue.
	 * 
	 * @param msg
	 *            the message to be inserted into the queue.
	 */
	public ReconfigurationException(String msg) {
		super(msg);
	}
}
