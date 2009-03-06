package arcademis;

/**
 * This exception is thrown as the result of a find operation for an object
 * that is not currently bound to the discovery agency.
 */
public class NotBoundException extends Exception implements Marshalable {

	/**
	 * Determines if a de-serialized file is compatible with this class.
	 * 
	 * Maintainers must change this value if and only if the new version of this
	 * class is not compatible with old versions. See Sun docs for <a
	 * href=http://java.sun.com/products/jdk/1.1/docs/guide
	 * /serialization/spec/version.doc.html> details. </a>
	 * 
	 */
	private static final long serialVersionUID = 7528366297140776147L;

	/**
	 * Empty constructor. Puts a standard message into the error stack.
	 */
	public NotBoundException() {
		super("Target remote object not bound");
	}

	/**
	 * This constructor inserts the given message into the error queue.
	 * @param msg the message to be inserted into the queue.
	 */
	public NotBoundException(String msg) {
		super(msg);
	}

	/**
	 * Fills the stream b with the byte sequence that describes this exception.
	 * @param the stream used in the serialization process.
	 * @throws MarshalException if it is not possible to serialize this object.
	 */
	public void marshal(Stream b) {}

	/**
	 * Fills the content of this exception with information retrived from a
	 * stream.
	 * @param the stream used in the serialization process.
	 */
	public void unmarshal(Stream b) {}
}