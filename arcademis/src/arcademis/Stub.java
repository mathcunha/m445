package arcademis;

/**
 * The stubs works as a proxy for the real implementation of the remote object.
 * When the client invokes methods on the remote object, it is actually
 * invoking the methods on a stub for that object. Any implementation of the
 * stub has the same interface as the remote object it represents.
 */
public abstract class Stub implements Marshalable, Remote {

	protected MultiReference mr = OrbAccessor.getMultiRef();

	/**
	 * This method returns the remote references of this stub. The remote
	 * reference contains all the information necessary to the stub
	 * to carry on communication with the object(s) it represents.
	 * @return an object of the <CODE>MultiReference</CODE> type.
	 */
	public MultiReference getRemoteReferences() {
		return this.mr;
	}

	/**
	 * Defines the remote reference that is used by this stub for communicating
	 * with a remote object.
	 * @param ref the new remote reference.
	 */
	public void attach(RemoteReference ref) {
		this.mr.attach(ref);
	}

	/**
	 * Determines the set of references that will be used by this stub in order
	 * to perform remote calls.
	 */
	public void setReferenceList(MultiReference mr) {
		this.mr = mr;
	}

	/**
	 * Fills the stream b with the byte sequence that describes this object.
	 * @throws MarshalException if it is not possible to serialize this object.
	 * @param the stream used in the serialization process.
	 */
	public void marshal(Stream b) throws MarshalException {
		b.write(this.mr);
	}

	/**
	 * Fills the content of this object with information retrived from a
	 * stream.
	 * @param the stream used in the serialization process.
	 */
	public void unmarshal(Stream b) throws MarshalException {
		this.mr = (MultiReference)b.readObject();
	}

	/**
	 * This method is responsible for creating a call with the given parameters
	 * and passing it to the server side of a distributed application.
	 * @param args the arguments of the call.
	 * @param opCode the code of the operation that is being invoked. In order
	 * to allow the communication between the stub and the skeleton, any
	 * remote operation is given a particular code.
	 * @param serviceCominator a <CODE>char</CODE> that describes the pattern for server
	 * invocation. This character may hold three different service combinators:
	 * sequential invocation (>), non-deterministic invocation (?) and concurrent
	 * invocation (|).
	 * @return NetworkException in case some error occurs during the invocation
	 * or remote processing of the call.
	 * @throws NetworkException if something goes wrong during the remote
	 * invocation.
	 */
	public abstract Stream invoke(Stream args, int opCode, char serviceCombinator, int priority)
	throws NetworkException;

	/**
	 * Returns a textual representation of this stub.
	 * @return an object of the <CODE>String</CODE> type that represents the
	 * contents of this object.
	 */
	public String toString() {
		return this.getClass().getName() + ": " + this.mr.toString();
	}
}
