package br.unifor.mia.cd.middleware.rme2;

import arcademis.*;
import br.unifor.mia.cd.middleware.rme2.rmep.*;

public class ChannelVerifier extends ProtocolHandler {

	private boolean isOpen = false;

	public void open(Channel ch) {
		try {
			super.protocol.setChannel(ch);

			// creating and sending the ping
			PingMsg ping = (PingMsg)OrbAccessor.getMessage(RmeConstants.PING_MESSAGE);
			super.protocol.send(ping);

			// receiving the acknowledge
			Message msg = super.protocol.recv();
			if(!(msg instanceof AckMsg))
				throw new ProtocolException("Received " +msg.getClass().getName()+ " while waiting for PING");
			else
				isOpen = true;
		} catch (NetworkException e) {
			isOpen = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleEvent(Event e){}

	public boolean isOpen() {
		return isOpen;
	}
}
