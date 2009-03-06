package arcademis;

/**
 * This interface determines the <CODE>Protocol</CODE> factory. Whenever necessary to
 * modify the implementation of this component it is sufficient to change the
 * factory's implementation so it will return the a different instance of
 * <CODE>Protocol</CODE>.
 */
public interface ProtocolFc {

	/**
	 * Creates a default protocol
	 * @return an object of the <CODE>Protocol</CODE> type.
	 */
	public Protocol createProtocol ();

	/**
	 * Creates a specific protocol
	 * @param objType defines the type of the invoker that will be created.
	 * @return an object of the <CODE>Protocol</CODE> type.
	 */
	public Protocol createProtocol (int objType);

}
