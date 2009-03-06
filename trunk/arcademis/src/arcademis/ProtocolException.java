package arcademis;

/**
 * This exception is thrown when a failure in the middleware protocol is
 * perceived. Such failures happen, for example, when a given message is being
 * expected and another one is actually received.
 */
public class ProtocolException extends ArcademisException {

	/**
	 * Determines if a de-serialized file is compatible with this class.
	 * 
	 * Maintainers must change this value if and only if the new version of this
	 * class is not compatible with old versions. See Sun docs for <a
	 * href=http://java.sun.com/products/jdk/1.1/docs/guide
	 * /serialization/spec/version.doc.html> details. </a>
	 * 
	 */
	private static final long serialVersionUID = 7528374151430776147L;

	/**
	 * Empty constructor. Puts a standard message into the error stack.
	 */
	public ProtocolException() {
		super("Protocol exception: wrong message type");
	}

	/**
	 * This constructor inserts the given message into the error queue.
	 * 
	 * @param msg
	 *            the message to be inserted into the queue.
	 */
	public ProtocolException(String msg) {
		super(msg);
	}
}
