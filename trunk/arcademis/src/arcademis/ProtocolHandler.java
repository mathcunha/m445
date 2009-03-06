package arcademis;


/**
 * This class define a service handler that uses a high-level protocol in order
 * to communicate with other entities.
 */
public abstract class ProtocolHandler extends ServiceHandler {

	/**
	 * Every RequestSender uses a specific protocol for transmitting messages.
	 */
	protected Protocol protocol = null;

	/**
	 * The protocol handler open method just associates the channel to the
	 * protocol implementation.
	 * @param ch the channel implementation used in the communicating process.
	 */
	public void open(Channel ch) {
		protocol.setChannel(ch);
	}

	/**
	 * The handle event is declared because the protocol sender is a service
	 * handler; therefore, it is a event handler.
	 */
	public void handleEvent(Event ec) {}

	/**
	 * Determines the service handler protocol.
	 * @param protocol the new protocol.
	 */
	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	/**
	 * This method returns a reference to the request sender protocol.
	 * @return the request receiver protocol.
	 */
	public Protocol getProtocol() {
		return this.protocol;
	}
}
