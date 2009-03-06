package arcademis;

/**
 * The connector component is the client representant in the acceptor-connector
 * design pattern. This pattern is used to decouple the process of connection
 * establishment from the usage that will be given to the channel after it has
 * been built.
 */
public abstract class Connector implements EventHandler {

    private int defaultConnectionTimeout = 0;

	/**
	 * This method establishes a connection with a specified acceptor and passes
	 * the just built channel to the service handler.
	 * @param h the service handler that will be used to process the channel after
	 * it has been generated by the connector.
	 * @param epid the location of the acceptor that will be contacted in order to
	 * generate a channel with this connector.
	 * @throws NetworkException in the case some error takes place in the
	 * transport layer.
	 */
    public abstract void connect(ServiceHandler h, Epid epid) throws NetworkException;

	/**
	 * The connector is an event handler, so it has to implement the handle event
	 * method. Normally, the connector has to handle events when an attemp to
	 * establish an assynchronous connection is made. In this case, the event is
	 * the connection establishment.
	 * @param e the event to be handled.
	 */
    public void handleEvent(Event e) {}

	/**
	 * Informs the time out of this connector. If an attempt to establish a
	 * connection is made, but the connector does not obtain any answer from the
	 * acceptor during the time out interval, an exception is thrown.
	 * @return an integer value that represents the time out in milliseconds.
	 */
    public int getDefaultConnectionTimeout() {
        return defaultConnectionTimeout;
    }

	/**
	 * Determines the time out of the connector
	 * @param t the new timeout, in milliseconds.
	 */
    public void setDefaultConnectionTimeout(int t) {
        defaultConnectionTimeout = t;
    }
}
