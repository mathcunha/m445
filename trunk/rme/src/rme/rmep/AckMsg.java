package rme.rmep;

import arcademis.MarshalException;
import arcademis.Message;
import arcademis.ProtocolHandler;
import arcademis.Stream;

/**
 * This class rempresent the acknowledge message, one of the four kinds of
 * messages defined in RMEP. The ack message is sent as an answer for the Ping
 * message. Every ping originates an acknowledge in the receiving application.
 */
public class AckMsg implements Message {
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
	 * Do nothing. Normally because the service handler that received this message
	 * already do all the processing that is necessary to handle the information
	 * the message carries.
	 * @param s an object of the <CODE>ProtocolHandler</CODE> type.
	 */
	public void execute(ProtocolHandler s) {
	}
}