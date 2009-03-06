package br.unifor.mia.cd.middleware.rme2.server;

import br.unifor.mia.cd.middleware.rme2.RmeConstants;
import arcademis.ArcademisConstants;
import arcademis.Channel;
import arcademis.Identifier;
import arcademis.Message;
import arcademis.NetworkException;
import arcademis.OrbAccessor;
import arcademis.Protocol;
import arcademis.Stream;
import arcademis.server.RequestReceiver;

/**
 * This implementation of request receiver keeps the channel working during a
 * certain amount of time, in order to allow the client reuse the same channel
 * successive times. It implements a best effor semantics.
 */
public class RmeRequestReceiver extends RequestReceiver implements
		RmeConstants, Runnable {

	/**
	 * This method starts a new thread to control the use to the channel.
	 * 
	 * @param ch
	 *            the channel to be processed.
	 */
	public void open(Channel ch) {
		ch.setConnectionTimeout(TOLERANCE_TIME);
		super.protocol.setChannel(ch);
		Thread t = new Thread(this);
		t.start();
	}

	/**
	 * Receives the messages, passes it to the dispatcher, collects the return
	 * value and forword it to a response receiver. If the channel stays a
	 * certain amount of time withoug being used, it is closed. This measure is
	 * takin in order to improve the security of the system, and in order to
	 * avoid that a client waits indefinitely if, somehow, the return message is
	 * lost across the network.
	 */
	public void run() {
		boolean isOperant = true;
		while (isOperant) {
			try {
				Message msg = super.protocol.recv();
				msg.execute(this);
			} catch (Exception e) {
				try {
					Channel ch = super.protocol.getChannel();
					ch.close();
				} catch (NetworkException ne) {
					e.printStackTrace();
				}
				isOperant = false;
			}
		}
	}

	public void sendReturnValue(Identifier callId, Stream returnStr,
			Protocol rmep) {
		RmeResponseSender sender = (RmeResponseSender) OrbAccessor
				.getServiceHandler(ArcademisConstants.RESPONSE_SENDER);
		sender.setProtocol(rmep);
		sender.setReturnValue(returnStr);
		sender.setCallIdentifier(callId);
		sender.open(rmep.getChannel());
	}
}
