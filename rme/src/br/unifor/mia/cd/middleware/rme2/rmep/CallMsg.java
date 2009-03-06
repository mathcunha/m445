package br.unifor.mia.cd.middleware.rme2.rmep;

import br.unifor.mia.cd.middleware.rme2.RmeRemoteCall;
import br.unifor.mia.cd.middleware.rme2.server.RmeRequestReceiver;
import arcademis.MarshalException;
import arcademis.Message;
import arcademis.ProtocolHandler;
import arcademis.Stream;
import arcademis.server.Dispatcher;
import arcademis.server.RequestReceiver;

/**
 * This class represents the call message, one of the four kinds of messages used
 * in RMEP. The call message describes a message containing all the values need
 * in order to carry on a remote method invocation.
 */
public class CallMsg implements Message {

	private RmeRemoteCall remoteCall = null;

	/**
	 * Determines the remote call paramteres that will be transmited by means
	 * of the message.
	 * @param remoteCall an object that encapsulates all the parameters of a
	 * remote call.
	 */
	public void setRemoteCall(RmeRemoteCall remoteCall) {
		this.remoteCall = remoteCall;
	}

	/**
	 * Gives a reference to the remote call that is or was transmited by this
	 * message.
	 * @return an object of the <CODE>RmeRemoteCall</CODE> type.
	 */
	public RmeRemoteCall getRemoteCall() {
		return this.remoteCall;
	}

	/**
	 * Fills the stream b with the byte sequence that describes this object.
	 * @throws MarshalException if it is not possible to serialize this object.
	 * @param the stream used in the serialization process.
	 */
	public void marshal(Stream b) throws MarshalException {
		b.write(this.remoteCall);
	}

	/**
	 * Fills the content of this object with information retrived from a
	 * stream.
	 * @param the stream used in the serialization process.
	 */
	public void unmarshal(Stream b) throws MarshalException {
		this.remoteCall = (RmeRemoteCall)b.readObject();
	}


	/**
	 * Passes the remote call to the dispatcher and sends the result back to the
	 * client.
	 * @param the request receiver that received this message.
	 */
	public void execute(ProtocolHandler s) {
		Dispatcher dispatcher = ((RequestReceiver)s).getDispatcher();
		Stream returnValue = dispatcher.dispatch(remoteCall);
		((RmeRequestReceiver)s).sendReturnValue(remoteCall.getCallIdentifier(), returnValue, s.getProtocol());
	}
}