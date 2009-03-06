package arcademis.server;

import arcademis.*;

/**
 * In Arcademis, distributed objects have to inherit from the <CODE>RemoteObject</CODE>
 * class, that determines the semantics of operations such as <CODE>equals</CODE>
 * and <CODE>hashcode</CODE> when locally invoked. In addition, remote objects must
 * implement the </CODE>Remote</CODE>
 * interface. Although this interface is empty, i.e., it does not declare any method, 
 * it is used by the system to distinguish references to local objects from
 * references to remote objects. For example, in remote invocations, the Arcademis
 * implementation should replace remote references by their associated stubs, 
 * in order  to simulate call by reference.
 */
public abstract class RemoteObject implements Active, Marshalable, Remote {

	/**
	 * Every remote object own a remote reference.
	 */
    protected RemoteReference remoteRef;

	/**
	 * This method determines how remote objects are initialized in Arcademis. In
	 * implementations of remote object, generally this method invokes the activation
	 * routine of the <CODE>Activator</CODE> component of the middleware.
	 * @throws ActivationException if an error is verified due to initializing the
	 * remote object.
	 */
    public abstract void activate() throws ActivationException;

	/**
	 * The implementation of this method should give back to the operating system
	 * all the resources allocated to the remote object by the
	 * <CODE>activate</CODE> operation.
	 * @throws ActivationException if an error is verified due to initializing the
	 * remote object.
	 */
    public abstract void deactivate() throws ActivationException;

	/**
	 * Returns a reference to the remote reference that is encapsulated by this
	 * object.
	 * @return an object of the <CODE>RemoteReference</CODE> type.
	 */
    public RemoteReference getRef() {
        return remoteRef;
    }

	/**
	 * Determines the remote reference that will be used by this object to
	 * represent it in the distributed system.
	 * @param remoteRef the new <CODE>RemoteReference</CODE>.
	 */
    public void setRef(RemoteReference remoteRef) {
        this.remoteRef = remoteRef;
    }

	/**
	 * This method determines the semantics of the <CODE>equals</CODE> method when
	 * it is invoked locally on this object. The remote semantics of this method
	 * is determined by the implementation of the <CODE>RemoteReference</CODE>
	 * class.
	 * @param o an object that will be compared againt this one.
	 * @return a <CODE>boolean</CODE> value that is true if the both objects are
	 * equal and false otherwise.
	 */
    public boolean equals(Object o) {
        if(!(o instanceof RemoteObject))
            return false;
        else
            return this.remoteRef.equals(((RemoteObject)o).remoteRef);
    }

	/**
	 * This method determines the semantics of the <CODE>hash</CODE> method when
	 * it is invoked locally on this object. The remote semantics of this method
	 * is determined by the implementation of the <CODE>RemoteReference</CODE>
	 * class.
	 * @return the remote object's hash code.
	 */
    public int hashCode() {
        return super.hashCode();
    }

	/**
	 * This method determines the semantics of the <CODE>toString</CODE> method when
	 * it is invoked locally on this object. The remote semantics of this method
	 * is determined by the implementation of the <CODE>RemoteReference</CODE>
	 * class.
	 * @return an <CODE>String</CODE> containing the textual representation of this
	 * object.
	 */
    public String toString() {
        if(remoteRef == null)
            return "RemoteObject with a null remote reference";
        else
            return "RemoteObject: " + remoteRef.toString();
    }

	/**
	 * The implementation of this method defines how remote objects generate
	 * stubs.
	 * @return an object of the <CODE>Stub</CODE> type.
	 */
    public abstract Stub getStub();

	/**
	 * The implementation of this method determines how remote objects generate
	 * skeletons.
	 * @return an object of the <CODE>Skeleton</CODE> type.
	 */
    public abstract Skeleton getSkeleton();

	/**
	 * Fills the stream b with the byte sequence that describes this object. The
	 * information to be serialized includes the remote reference encapsulated by
	 * this object.
	 * @throws MarshalException if it is not possible to serialize this object.
	 * @param the stream used in the serialization process.
	 */
    public void marshal(Stream b) throws MarshalException {
        b.write(remoteRef);
    }

	/**
	 * Fills the content of this object with information retrived from a
	 * stream.
	 * @param the stream used in the serialization process.
	 */
    public void unmarshal(Stream b) throws MarshalException {
        this.remoteRef = (RemoteReference)b.readObject();
    }
}