package br.unifor.mia.cd.middleware.rme2.rmep;

import arcademis.*;

public class RmeMessageFc implements MessageFc, br.unifor.mia.cd.middleware.rme2.RmeConstants {

	public Message createMessage () {
		return null;
	}

	public Message createMessage (int objType) {
		switch(objType) {
			case CALL_MESSAGE: return new CallMsg();
			case RETURN_MESSAGE: return new RetMsg();
			case PING_MESSAGE: return new PingMsg();
			case PING_ACKNOWLEDGE: return new AckMsg();
			default: return null;
		}
	}

}
