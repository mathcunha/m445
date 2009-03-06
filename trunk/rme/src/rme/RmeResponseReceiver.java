package rme;

import rme.rmep.*;
import arcademis.*;

/**
 * This class implements a best-effort response receiver.
 */
public class RmeResponseReceiver extends ResponseReceiver {

	/**
	 * This method just receives a message and verifies if it holds a return
	 * valued, that is, the value that is spected from a remote method execution.
	 */
	public void open(Channel ch) {
		try {
			super.protocol.setChannel(ch);
			Message msg = super.protocol.recv();
			if(!(msg instanceof RetMsg)) {
				String unknowClassName = msg.getClass().getName();
				throw new ProtocolException("Wrong message type: " + unknowClassName);
			}
			super.future = ((RetMsg)msg).getReturnValue();
		} catch (ArcademisException e) {
			e.printStackTrace();
		}
	}
}