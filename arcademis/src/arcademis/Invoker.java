package arcademis;

/**
 * This component implements the invocation strategy. It is responsible for
 * sending to the remote object the parameters of the call and receiving back
 * the result. The invocation strategy defines:
 * <UL>
 * <LI> if the channel is reused or a new channel is created at each new call.
 * <LI> if the call is assynchronous or synchronous.
 * <LI> how many remote references are used in order to perform load balancing
 * and to improve fault tolerance.
 * <UL>
 */
public interface Invoker {
	/**
	 * This method carries on the remote method invocation. It send to the
	 * remote object the parameters stored in the remote call and receives back
	 * the results obtained in the call processing.
	 * @param c an object of the <CODE>RemoteCall</CODE> type.
	 * @param ref a remote reference to the object that will process the call.
	 * @return a <CODE>Stream</CODE> that contains the return value obtained
	 * after the processing of the call.
	 * @throws NetworkException if the remote call cannot by performed due to
	 * problems in the transport network.
	 */
	public Stream invoke(RemoteCall c, RemoteReference ref) throws NetworkException;

	/**
	 * This method carries on the remote method invocation. It send to the
	 * remote object the parameters stored in the remote call and receives back
	 * the results obtained in the call processing.
	 * @param c an object of the <CODE>RemoteCall</CODE> type.
	 * @param mRef a set of remote references to objects that will process the
	 * call. Depending on the invocation strategy, the call may be executed by
	 * one of the servers, by all of them of by a subset of the multi reference
	 * set.
	 * @return a <CODE>Stream</CODE> that contains the return value obtained
	 * after the processing of the call.
	 * @throws NetworkException if the remote call cannot by performed due to
	 * problems in the transport network.
	 */
	public Stream invoke(RemoteCall c, MultiReference mRef) throws NetworkException;
}
