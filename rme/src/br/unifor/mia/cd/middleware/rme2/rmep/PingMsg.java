package br.unifor.mia.cd.middleware.rme2.rmep;

import arcademis.*;
import br.unifor.mia.cd.middleware.rme2.RmeConstants;

/**
 * This class rempresent the ping message, one of the four kinds of messages used
 * in RMEP.
 */
public class PingMsg implements Message {
	/**
	 * Fills the stream b with the byte sequence that describes this object.
	 * @throws MarshalException if it is not possible to serialize this object.
	 * @param the stream used in the serialization process.
	 */
	public void marshal(Stream b) throws MarshalException {}

	/**
	 * Fills the content of this object with information retrived from a
	 * stream.
	 * @param the stream used in the serialization process.
	 */
	public void unmarshal(Stream b) throws MarshalException {}

	/**
	 * Send an acknowledge back to the entity that issued the ping messsage.
	 * @param the request receiver that received this message.
	 */
	public void execute(ProtocolHandler s) {
		AckMsg ack = (AckMsg)OrbAccessor.getMessage(RmeConstants.PING_ACKNOWLEDGE);
		try {
			s.getProtocol().send(ack);
		} catch (ArcademisException e) {
			e.printStackTrace();
		}
	}
}