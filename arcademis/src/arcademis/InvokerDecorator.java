package arcademis;

/**
 * The invoker decorator is used to aggregate new functionality to the invoker.
 * Chains of these decorators can be assembled in an almost independent way.
 * Examples of new capabilities that can be added to the invoker by means of
 * decorators include log generation, assynchronous calls handling and caches.
 */
public class InvokerDecorator implements Invoker {

	/**
	 * The decorated invoker
	 */
	protected Invoker invoker = null;

	/**
	 * Creates a new invoker decorating the given parameter.
	 * @param invoker the <CODE>Invoker</CODE> to be decorated.
	 */
	public InvokerDecorator(Invoker invoker) {
		this.invoker = invoker;
	}

	/**
	 * A decorator always encapsulates a decorated object. This method allows to
	 * define such enclosed object.
	 * @param invoker the <CODE>Invoker</CODE> to be decorated.
	 */
	public void setInternalInvoker(Invoker invoker) {
		this.invoker = invoker;
	}

	/**
	 * This method intercepts the invocation, and possibly changes its parameters,
	 * in order to add to them the specific capabilities provided by this invoker.
	 * Because the invoker calls the <CODE>invoke</CODE> routine of the decorated
	 * components, changes in the call's parameter can be done before and after
	 * the invocation.
	 * @param c an object of the <CODE>RemoteCall</CODE> type.
	 * @param ref a remote reference to the object that will process the call.
	 * @return a <CODE>Stream</CODE> that contains the return value obtained
	 * after the processing of the call.
	 * @throws NetworkException if the remote call cannot by performed due to
	 * problems in the transport network.
	 */
	public Stream invoke(RemoteCall c, RemoteReference ref) throws NetworkException {
		return this.invoker.invoke(c, ref);
	}

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
	public Stream invoke(RemoteCall c, MultiReference mRef) throws NetworkException {
		return this.invoker.invoke(c, mRef);
	}
}