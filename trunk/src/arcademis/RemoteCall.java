package arcademis;

/**
 * This class contains all the parameters of a remote call and the return value
 * originated from its processing. All remote calls contains a reference for
 * the remote entity that is responsible for processing it. They also contains
 * a list of arguments and a reference for the return value generated from its
 * processing. Further parameters can be added in the extension of this class.
 */
public class RemoteCall implements Marshalable {

	/**
	 * Fills the stream b with the byte sequence that describes this object.
	 * @throws MarshalException if it is not possible to serialize this object.
	 * @param the stream used in the serialization process.
	 */
	public void marshal(Stream b) throws MarshalException {}

	/**
	 * Fills the content of this object with information retrived from a
	 * stream.
	 * @param the stream used in the serialization process.
	 */
	public void unmarshal(Stream b) throws MarshalException {}

}
