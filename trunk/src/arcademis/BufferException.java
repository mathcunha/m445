package arcademis;

/**
 * This exception indicates that an error has occurred during buffer
 * manipulation. Such erros can be, for example, an attempt to insert data into
 * a buffer completly full, or to retrieve data from an empty buffer.
 */
public class BufferException extends ArcademisException {

	/**
	 * Determines if a de-serialized file is compatible with this class.
	 * 
	 * Maintainers must change this value if and only if the new version of this
	 * class is not compatible with old versions. See Sun docs for <a
	 * href=http://java.sun.com/products/jdk/1.1/docs/guide
	 * /serialization/spec/version.doc.html> details. </a>
	 * 
	 */
	private static final long serialVersionUID = 7528374155622771145L;

	/**
	 * Empty constructor. Puts a standard message in the error stack.
	 */
	public BufferException() {
		super("Exception in buffer");
	}

	/**
	 * This constructor inserts the given message into the error queue.
	 * @param msg the message to be inserted into the queue.
	 */
	public BufferException(String msg) {
		super(msg);
	}
}