package rme.rmep;

import arcademis.*;

/**
 * This class implements RMEP, the RME Protocol. This protocol contains four
 * types of messages:
 * <CODE>CALL</CODE>: represents a remote method invocation. Call messages
 * contains all the parameters of a remote method invocation.
 * <CODE>RETURN</CODE>: message holding the return value obtained from the
 * processing of a remote method invocation.
 * <CODE>PING</CODE>: message used to test if the channel is alive. A ping
 * message always originate an answer from the receiving entity.
 * <CODE>ACK</CODE>: the answer for a ping message.
 */
public class RmeProtocol extends Protocol {

	/**
	 * The RMEP recv receives a byte sequence from the communicating
	 * channel and use it to generate a message that is returned. Before
	 * returning the message, this method implementation verifies its type, in
	 * order to return the correct message.
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
		Message msg = super.recv();
		if (msg instanceof CallMsg) {
			CallMsg cMsg = (CallMsg)msg;
			return cMsg;
		} else if (msg instanceof RetMsg) {
			RetMsg rMsg = (RetMsg)msg;
			return rMsg;
		} else if (msg instanceof AckMsg) {
			AckMsg aMsg = (AckMsg)msg;
			return aMsg;
		} else if (msg instanceof PingMsg) {
			PingMsg pMsg = (PingMsg)msg;
			return pMsg;
		}
		return msg;
	}
}
