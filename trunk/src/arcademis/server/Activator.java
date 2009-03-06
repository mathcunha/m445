package arcademis.server;

/**
 * This class determines the initialization policy of an instancy of Arcademis.
 * Such policy defines, for example, what resources will be alocated to the
 * remote object by the operating system and how those resources will be put to
 * work.
 */
public abstract class Activator implements Active {

	/**
	 * Every activator is responsible for the initialization of one remote object.
	 */
	protected RemoteObject remoteObject;

	/**
	 * Associates this activator with the given instance of remote object.
	 * @param remoteObject the remote object that will be bound to this one.
	 */
	public void setRemoteObject(RemoteObject remoteObject) {
		this.remoteObject = remoteObject;
	}

	/**
	 * This method determines the activation policy used by the middleware. It is
	 * responsible for allocating all the necessary resources to the remote object
	 * initialization, such as data structures and threads.
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

}