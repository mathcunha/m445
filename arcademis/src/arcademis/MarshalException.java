package arcademis;

/**
 * This exception indicates that an error in the serialization process has
 * occurred. Generally, such erros are given by attempts to read data from an
 * empty stream or by attempts to read data by means of the wrong reading
 * method.
 */
public class MarshalException extends ArcademisException {

	/**
	 * Determines if a de-serialized file is compatible with this class.
	 * 
	 * Maintainers must change this value if and only if the new version of this
	 * class is not compatible with old versions. See Sun docs for <a
	 * href=http://java.sun.com/products/jdk/1.1/docs/guide
	 * /serialization/spec/version.doc.html> details. </a>
	 * 
	 */
	private static final long serialVersionUID = 7528908755622776147L;

	/**
	 * Empty constructor. Puts a standard message in the error stack.
	 */
	public MarshalException() {
		super("Error: marshal exception");
	}

	/**
	 * This constructor inserts the given message into the error queue.
	 * 
	 * @param msg
	 *            the message to be inserted into the queue.
	 */
	public MarshalException(String msg) {
		super(msg);
	}
}
