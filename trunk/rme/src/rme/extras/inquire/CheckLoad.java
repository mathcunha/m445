package rme.extras.inquire;

import arcademis.*;

public class CheckLoad extends ProtocolHandler {

	private long load = 0;

	public void open(Channel ch) {
		try {
			super.protocol.setChannel(ch);

			// creating and sending the inquiry
			InqMsg inq = new InqMsg();
			super.protocol.send(inq);

			// receiving the response
			Message msg = super.protocol.recv();
			if(!(msg instanceof LoadMsg))
				throw new ProtocolException();
			else {
				LoadMsg lMsg = (LoadMsg)msg;
				Stream returnValue = lMsg.getLoad();
				this.load = returnValue.readLong();
			}
		} catch (Exception e) {}
	}

	public void handleEvent(Event e){}

	public long getLoad() {
		return load;
	}
}
