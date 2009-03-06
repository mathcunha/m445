package br.unifor.mia.cd.middleware.rme2.server;

import arcademis.*;
import arcademis.server.*;
import arcademis.concreteComponents.*;

public class RmeAcceptorFc implements AcceptorFc {

	public Acceptor createAcceptor () {
		HostPortEpid epid = new HostPortEpid();
		epid.setHostName("localhost");
		epid.setPortNumber(0);
		return new BlockingAcceptor(epid);
	}

	public Acceptor createAcceptor (int objType) {
		HostPortEpid epid = new HostPortEpid();
		epid.setHostName("localhost");
		epid.setPortNumber(0);
		return new BlockingAcceptor(epid);
	}

	public Acceptor createAcceptor (Epid epid) {
		return new BlockingAcceptor(epid);
	}

}
