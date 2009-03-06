package arcademis;

/**
 * This exception indicates that an <CODE>Epid</CODE> element has been
 * constructed with inconsistent data. Although this exception is part of the
 * arcademis package, it does not inherit of <CODE>ArcademisException</CODE>,
 * even though it implements the marshal and unmarshal methods, so it and its
 * subclasses may be sent across the network carrying further information.
 */
public class MalformedAddressException extends Exception implements Marshalable {

	/**
	 * Determines if a de-serialized file is compatible with this class.
	 * 
	 * Maintainers must change this value if and only if the new version of this
	 * class is not compatible with old versions. See Sun docs for <a
	 * href=http://java.sun.com/products/jdk/1.1/docs/guide
	 * /serialization/spec/version.doc.html> details. </a>
	 * 
	 */
	private static final long serialVersionUID = 7528374155622888847L;

	/**
	 * Empty constructor. Puts a standard message in the error stack.
	 */
	public MalformedAddressException() {
		super("Target remote object not bound");
	}

	/**
	 * This constructor inserts the given message into the error queue.
	 * @param msg the message to be inserted into the queue.
	 */
	public MalformedAddressException(String msg) {
		super(msg);
	}

	/**
	 * Determines how this exception will be converted into a byte sequence.
	 * Because this class does not have any attribute, the marshal method has a
	 * empty body.
	 */
	public void marshal(Stream b) {}

	/**
	 * Determines how this exception may be retrieved from a byte sequence.
	 * Because this class does not have any attribute, the marshal method has a
	 * empty body.
	 */
	public void unmarshal(Stream b) {}
}