package br.unifor.mia.cd.middleware.rme2;

import arcademis.*;
import br.unifor.mia.cd.middleware.rme2.rmep.*;

/**
 * Synchronous request sender that implements the best effort semantics. This
 * implementation does not create any new thread, being executed in the same
 * thread of the calling application.
 * <P>
 * The RmeRequestSender implementation stores the communication channel
 * because it can be reused in another method invocation. The RME stub
 * tries to recycle the communication channel.
 */
public class RmeRequestSender extends RequestSender {

	/**
	 * The remoteCall object stores the parameter of the call and, after its
	 * execution, it also holds the return value.
	 */
	private RmeRemoteCall remoteCall = null;

	private Stream future = null;

	/**
	 * This request sender is really simple. It just sends the message that has
	 * been associated to it in the stub by means of the protocol instance that
	 * also has been associated with it in the implementation of
	 * <CODE>RmeStub</CODE>.
	 * @param ch the channel implementation used to transmit the message.
	 */
	public void open(Channel ch) {
		try {
			super.protocol.setChannel(ch);
			remoteCall.setReturnAddress(ch.getLocalEpid());
			// Message bulding
			CallMsg callMsg = (CallMsg)OrbAccessor.getMessage(RmeConstants.CALL_MESSAGE);
			callMsg.setRemoteCall(remoteCall);

			// Message transmission
			super.protocol.send(callMsg);

			// Creation of the entity responsible for receving the answer
			RmeResponseReceiver r = (RmeResponseReceiver)OrbAccessor.getServiceHandler(ArcademisConstants.RESPONSE_RECEIVER);
			r.setProtocol(super.protocol);
			r.open(ch);
			future = r.getFuture();
		} catch (ArcademisException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Determines the remote call to be performed.
	 * @param remoteCall the remote call that will be performed by this
	 * request sender.
	 */
	public void setRemoteCall(RmeRemoteCall remoteCall) {
		this.remoteCall = remoteCall;
	}

	/**
	 * Returns the remote call that is hold by this request sender.
	 * @return an object of the <CODE>RemoteCall</CODE> type.
	 */
	public RemoteCall getRemoteCall() {
		return this.remoteCall;
	}

	/**
	 * Returns the communication channel that is hold by this request sender.
	 * @return an object of the <CODE>Channel</CODE> type.
	 */
	public Channel getChannel() {
		return super.protocol.getChannel();
	}

	/**
	 * Return the valued obtained as the result of the remote call.
	 * @return an object of the <CODE>Stream</CODE> type.
	 */
	public Stream getFuture() {
		return this.future;
	}
}
