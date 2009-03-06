package br.unifor.mia.cd.middleware.rme2;

import arcademis.*;
import arcademis.concreteComponents.*;

public class RmeRemoteRefFc implements RemoteRefFc {

	public RemoteReference createRemoteRef () {
		HostPortEpid epid = (HostPortEpid)OrbAccessor.getEpid();
		epid.setHostName("localhost");
		epid.setPortNumber(0);
		Identifier id = OrbAccessor.getIdentifier();
		return new RemoteReference(epid, id);
	}

	public RemoteReference createRemoteRef (int objType) {
		return createRemoteRef();
	}

	public MultiReference createMultiRef () {
		return new MultiReferenceImpl();
	}

	public RemoteReference createRemoteRef (Epid epid) {
		Identifier id = OrbAccessor.getIdentifier();
		return new RemoteReference(epid, id);
	}

	public RemoteReference createRemoteRef (Epid epid, Identifier id) {
		return new RemoteReference(epid, id);
	}
}
