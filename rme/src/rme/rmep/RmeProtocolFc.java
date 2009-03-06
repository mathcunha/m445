package rme.rmep;

import arcademis.*;

public class RmeProtocolFc implements ProtocolFc {

	public Protocol createProtocol () {
		return new RmeProtocol();
	}

	public Protocol createProtocol (int objType) {
		return new RmeProtocol();
	}

}