package arcademis;

/**
 * This class implements the response receiver component. This
 * entity's function is to wait for the incoming of the return value of a
 * remote method invocation. This component is also responsible for the
 * implementation of the middleware protocol from the client's point of
 * view.
 */
public class ResponseReceiver extends ProtocolHandler {
	protected Stream future = null;

	/**
	 * Determines the future stream that will be associated with this object.
	 * @param future an object of the <CODE>Stream</CODE> type.
	 */
	public void setFuture(Stream future) {
		this.future = future;
	}

	/**
	 * Returns a reference to the future stream held by this object.
	 * @return an object of the <CODE>Stream</CODE> type.
	 */
	public Stream getFuture() {
		return this.future;
	}
}
