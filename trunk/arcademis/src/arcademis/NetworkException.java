package arcademis;

/**
 * This exception indicates that an error has occurred while data was being
 * transmitted across the network. Such erros may happen, for example, when an
 * attempt to contact an of-line server takes place.
 */
public class NetworkException extends ArcademisException {

	/**
	 * Determines if a de-serialized file is compatible with this class.
	 * 
	 * Maintainers must change this value if and only if the new version of this
	 * class is not compatible with old versions. See Sun docs for <a
	 * href=http://java.sun.com/products/jdk/1.1/docs/guide
	 * /serialization/spec/version.doc.html> details. </a>
	 * 
	 */
	private static final long serialVersionUID = 7528374155629876543L;

	/**
	 * Empty constructor. Puts a standard message in the error stack.
	 */
	public NetworkException() {
		super("Error: network exception");
	}

	/**
	 * This constructor inserts the given message into the error queue.
	 * 
	 * @param msg
	 *            the message to be inserted into the queue.
	 */
	public NetworkException(String msg) {
		super(msg);
	}
}
