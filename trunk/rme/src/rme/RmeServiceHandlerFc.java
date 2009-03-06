package rme;

import rme.server.*;
import arcademis.*;
import arcademis.server.*;

public class RmeServiceHandlerFc implements ServiceHandlerFc, ArcademisConstants {

	public ServiceHandler createServiceHandler() {
		return null;
	}

	public ServiceHandler createServiceHandler (int objType) {
		switch(objType) {
			case CHANNEL_VERIFIER: return new ChannelVerifier();
			case REQUEST_SENDER: return createRequestSender();
			case REQUEST_RECEIVER: return createRequestReceiver();
			case RESPONSE_SENDER: return createResponseSender();
			case RESPONSE_RECEIVER: return createResponseReceiver();
			default: return null;
		}
	}

    public RequestSender createRequestSender() {
		return new RmeRequestSender();
	}

	public RequestReceiver createRequestReceiver() {
		return new RmeRequestReceiver();
	}

    public ResponseSender createResponseSender() {
		return new RmeResponseSender();
	}

    public ResponseReceiver createResponseReceiver() {
		return new RmeResponseReceiver();
	}
}