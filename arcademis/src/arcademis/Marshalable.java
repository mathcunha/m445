package arcademis;

/**
 * This interface defines the serialization strategy adopted by each class in
 * order to serialize its instances. To serialize an object means to convert it
 * into a raw sequence of bytes, so it may be sent across a network or recorded
 * in persistent media. Every serializable object, according to the Arcademis's
 * specification, must implement this interface. The serialization protocol is
 * defined by the implementaion of the <CODE>Stream</CODE> interface.
 * The following class is an example of implementation of the Marshalable
 * interface:
 * <P>
 * <PRE>
 * import arcademis.*;
 *
 * public class Person implements Marshalable {
 * private String name = null;
 * private int age = null;
 * private boolean isMan = null;
 * 
 *   public void marshal(Stream b) throws MarshalException {
 *     b.write(name);
 *     b.write(age);
 *     b.write(isMan);
 *   }
 * 
 *   public void unmarshal(Stream b) throws MarshalException
 *     name = (String)b.readObject();
 *     age = b.readInt();
 *     isMan = b.readBoolean();
 *   }
 * }
 * </PRE>
 */
public interface Marshalable {

	/**
	 * This method determines how the internal state of an object will be
	 * transcripted to a raw sequence of bytes.
	 * @param b the <CODE>Stream</CODE> that will receive the data that
	 * constitutes the current's object internal state.
	 * @throws MarshalException if the serialization process cannot be completed.
	 */
    public void marshal(Stream b) throws MarshalException;

	/**
	 * This method defines how the content of an object can be retrieved from a
	 * raw sequence of bytes.
	 * @param b the <CODE>Stream</CODE> that will be read so that the new content
	 * of the object can be obtained.
	 * @throws MarshalException if the serialization process cannot be completed.
	 */
    public void unmarshal(Stream b) throws MarshalException;

}