package arcademis;

/**
 * A protocol defines the set of messages that characterizes a transaction in
 * the middleware. It is possible to use the polimorfic capacities of the
 * Java language in order to define a wide set of different actions for the
 * send and receive methods. This class mainly works as a bridge connecting
 * channels' implementations and messages. Because channels handle byte
 * sequences, messages need to be converted into such format before being
 * passed to channels. For the same reason, after data is available in the
 * channel, it need to be transformed into typed messages so they can be
 * handled by the service handlers.
 */
public class Protocol {
	private Channel ch = null;

	/**
	 * The basic send of arcademis.Protocol simply extracts the content of the
	 * message and send it across the internal communicating channel.
	 * @param msg an Message object that holds the byte sequence that should be
	 * sent throw the channel.
	 * @throws ProtocolException if the message does not contain an adequate
	 * content.
	 * @throws MarshalException if the marshaling algorithm has not been
	 * correctly implemented.
	 * @throws NetworkException if occurs some failure in the communicating
	 * channel.
	 */
	public void send(Message msg)
	throws ProtocolException, NetworkException {
		if (ch == null)
			throw new ProtocolException("Attemp to send message across null channel");
		try {
			Stream b = OrbAccessor.getStream();
			b.write(msg);
			ch.send(b.getBytes());
		} catch (MarshalException e) {
			throw new ProtocolException(e.getMessage());
		}
	}



	/**
	 * The basic recv simply receives a byte sequence from the communicating
	 * channel and use it to generate a message that is returned.
	 * @return a Message object holding the received byte sequence.
	 * @throws ProtocolException if the message does not contain an adequate
	 * content.
	 * @throws MarshalException if the marshaling algorithm has not been
	 * correctly implemented.
	 * @throws NetworkException if occurs some failure in the communicating
	 * channel.
	 */
	public Message recv()
	throws ProtocolException, NetworkException {
		if (ch == null)
			throw new ProtocolException("Attemp to receive message across null channel");
		Message msg = null;
		try {
			Stream b = OrbAccessor.getStream();
			byte[] s = ch.recv();
			if(s == null)
				throw new NetworkException("Null message received in protocol");
			b.fill(s);
			msg = (Message)b.readObject();
		} catch (MarshalException e) {
			e.printStackTrace();
			throw new ProtocolException(e.getMessage());
		}
		return msg;
	}

	/**
	 * Associates a channel to this protocol.
	 * @param ch the channel that will be associated with this protocol.
	 */
	public void setChannel(Channel ch) {
		this.ch = ch;
	}


	/**
	 * Returns a reference for the channel the protocol uses to send messages.
	 * @return an object of the <CODE>channel</CODE> type.
	 */
	public Channel getChannel() {
		return this.ch;
	}
}
