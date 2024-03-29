package arcademis;

/**
 * This exception indicates that the server has received a remote method
 * invocation that has not been issued by a stub generated by the remote object
 * the message targets.
 */
public class IncompatibleStubException extends ArcademisException {

	/**
	 * Determines if a de-serialized file is compatible with this class.
	 * 
	 * Maintainers must change this value if and only if the new version of this
	 * class is not compatible with old versions. See Sun docs for <a
	 * href=http://java.sun.com/products/jdk/1.1/docs/guide
	 * /serialization/spec/version.doc.html> details. </a>
	 * 
	 */
	private static final long serialVersionUID = 3999174155622776147L;

	/**
	 * Empty constructor. Puts a standard message in the error stack.
	 */
	public IncompatibleStubException() {
		super("Attempt to use wrong stub in communication with remote object");
	}

	/**
	 * This constructor inserts the given message into the error queue.
	 * 
	 * @param msg
	 *            the message to be inserted into the queue.
	 */
	public IncompatibleStubException(String msg) {
		super(msg);
	}
}