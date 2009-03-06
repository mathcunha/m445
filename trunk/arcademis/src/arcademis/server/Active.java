package arcademis.server;

/**
 * This interface marks the objects that can be initialized in order to receive
 * remote calls. The <CODE>RemoteObject</CODE> class implements this interface.
 */
public interface Active {

	/**
	 * Initializes the object so it is ready to receive remote calls.
	 * @throws ActivationException if an error ocurrs during the object
	 * activation.
	 */
    public void activate() throws ActivationException;

	/**
	 * Finishes activities in the target object.
	 * @throws ActivationException if an error ocurrs during the object
	 * activation.
	 */
    public void deactivate() throws ActivationException;
}