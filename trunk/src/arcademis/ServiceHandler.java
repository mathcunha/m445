package arcademis;

/**
 * Service handlers are components whose purpose it to process communication
 * channels that have been created by the acceptor and connector elements.
 * Service handlers, acceptors and connectors constitutes what is called the
 * acceptor-connector design pattern. The main advantage of this pattern is the
 * possibility of modifying the strategy of connection set up without changing
 * the code of the service handlers. The opposit is also true: modifications in
 * the code of the service handlers do not alter the implementation of the
 * components responsible for the connection establishment.
 */
public abstract class ServiceHandler implements EventHandler {

	/**
	 * This method starts the connectin processing, what may be done in the
	 * same thread the method has been invoked, or in a different one.
	 * @param ch the channel to be processed, that is, that will be used by the
	 * service handler to send and receive messages.
	 */
    public abstract void open(Channel ch);

	/**
	 * This method is declared without a body only because the service handler
	 * components implements the <CODE>EventHandler</CODE> interface.
	 * @param e the event to be handled.
	 */
    public void handleEvent(Event e) {}
}
