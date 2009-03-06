package arcademis;

/**
 * This exception is throw in the stub code whenever an unknown exception is
 * captured there. The class name of the unknown exception is normally passed as
 * the message of this exception.
 */
public class UnspecifiedException extends ArcademisException implements
		Marshalable {

	/**
	 * Determines if a de-serialized file is compatible with this class.
	 * 
	 * Maintainers must change this value if and only if the new version of this
	 * class is not compatible with old versions. See Sun docs for <a
	 * href=http://java.sun.com/products/jdk/1.1/docs/guide
	 * /serialization/spec/version.doc.html> details. </a>
	 * 
	 */
	private static final long serialVersionUID = 7528374155622333247L;

	/**
	 * Empty constructor. Puts a standard message into the error stack.
	 */
	public UnspecifiedException() {
		super("Error: exception in the middleware layer");
	}

	/**
	 * This constructor inserts the given message into the error queue.
	 * 
	 * @param msg
	 *            the message to be inserted into the queue.
	 */
	public UnspecifiedException(String msg) {
		super(msg);
	}
}