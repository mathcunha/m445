package arcademis.server;

/**
 * This interface determines the dispatcher factory. Whenever necessary to
 * modify the dispatching strategy used by the middleware
 * platform, it is sufficient to change the factory's implementation so it will
 * return the proper chain of dispatchers.
 */
public interface DispatcherFc {
	/**
	 * Creates a default dispatcher
	 * @return an object of the <CODE>Dispatcher</CODE> type.
	 */
	public Dispatcher createDispatcher ();

	/**
	 * Creates a specific dispatcher
	 * @param objType defines the type of the identifier that will be created.
	 * @return an object of the <CODE>Dispatcher</CODE> type.
	 */
    public Dispatcher createDispatcher (int objType);
}