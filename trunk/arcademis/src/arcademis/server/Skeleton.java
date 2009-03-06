package arcademis.server;

import arcademis.*;

/**
 * The skeleton is like a method demultiplexer. It receives descriptions of
 * remote calls and pass them to the remote object that the skeleton
 * encapsulates.
 */
public abstract class Skeleton {

	/**
	 * This remote object will be responsible for processing the calls passed
	 * to this skeleton.
	 */
	protected RemoteObject remoteObject;

	/**
	 * This method receives a remote call and determines to what method the
	 * call parameters should be passed. Then it desserializes the arguments of
	 * the call and invokes on the remote object the proper method passing to
	 * it the just obtained arguments.
	 * @param remoteCall the call to be processed.
	 * @return an object of the <CODE>Stream</CODE> type that holds the
	 * serialized result of the call.
	 * @throws Exception actually, the exception is normally thrown by the
	 * method that will be executed. If this method throws an exception, it
	 * must be returned to the client application.
	 */
	public abstract Stream dispatch(RemoteCall remoteCall) throws Exception;

	/**
	 * Returns a reference to the remote object that is encapsulated by this
	 * skeleton.
	 * @return a reference to a remote object.
	 */
	public RemoteObject getRemoteObject() {
		return remoteObject;
	}

	/**
	 * Determines the remote object that will be encapsulated by this skeleton.
	 * @param remoteObject the new remote object reference that will be bound
	 * to this skeleton.
	 */
	public void setRemoteObject(RemoteObject remoteObject) {
		this.remoteObject = remoteObject;
	}
}
