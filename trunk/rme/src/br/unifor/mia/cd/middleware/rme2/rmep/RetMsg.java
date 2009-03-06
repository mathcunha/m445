package br.unifor.mia.cd.middleware.rme2.rmep;

import arcademis.Identifier;
import arcademis.MarshalException;
import arcademis.Message;
import arcademis.ProtocolHandler;
import arcademis.Stream;

/**
 * This class rempresent the return message, one of the four kinds of messages
 * used in RMEP. This message contains the return value originated by the
 * remote execution of a method invocation.
 */
public class RetMsg implements Message {
	private Identifier callIdentifier = null;

	private Stream returnValue = null;

	/**
	 * Determines the remote call paramteres that will be transmited by means
	 * of the message.
	 * @param remoteCall an object that encapsulates all the parameters of a
	 * remote call.
	 */
	public void marshal(Stream b) throws MarshalException {
		// send call identifier:
		b.write(this.callIdentifier);

		// append the return value:
		b.append(this.returnValue);
	}

	/**
	 * Gives a reference to the remote call that is or was transmited by this
	 * message.
	 * @return an object of the <CODE>RmeRemoteCall</CODE> type.
	 */
	public void unmarshal(Stream b) throws MarshalException {
		// reading the identifier of the call
		this.callIdentifier = (Identifier)b.readObject();

		// reading the value returned by the call
		this.returnValue = b;
	}

	/**
	 * Determines the identifier of this message. In RME, every remote
	 * invocation has an identifier that is used by the dispatcher
	 * implementation in order to verify if the call really comes from a
	 * valid stub.
	 * @param callIdentifier the new identifier of the call.
	 */
	public void setCallIdentifier(Identifier callIdentifier) {
		this.callIdentifier = callIdentifier;
	}

	/**
	 * Defines the value that will be transmitted by this message. This message
	 * encapsulates the return value of a remote method invocation.
	 * @param returnValue the value to be returned.
	 */
	public void setReturnValue(Stream returnValue) {
		this.returnValue = returnValue;
	}

	/**
	 * Gives a reference to the identifier of this call.
	 * @return an object of the <CODE>arcademis.Identifier</CODE> type.
	 */
	public Identifier getCallIdentifier() {
		return this.callIdentifier;
	}

	/**
	 * Returns a reference to the return value that is encapsulated by this
	 * message.
	 * @return an object of the <CODE>arcademis.Stream</CODE> type.
	 */
	public Stream getReturnValue() {
		return this.returnValue;
	}

	/**
	 * Do nothing. Normally because the service handler that received this message
	 * already do all the processing that is necessary to handle the information
	 * the message carries.
	 * @param s an object of the <CODE>ProtocolHandler</CODE> type.
	 */
	public void execute(ProtocolHandler s) {
	}
}