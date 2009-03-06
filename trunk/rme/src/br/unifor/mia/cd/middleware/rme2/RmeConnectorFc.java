package br.unifor.mia.cd.middleware.rme2;

import arcademis.Connector;
import arcademis.ConnectorFc;
import arcademis.concreteComponents.SynchronousConnector;

public class RmeConnectorFc implements ConnectorFc {

	public Connector createConnector () {
		return new SynchronousConnector();
	}

	public Connector createConnector (int objType) {
		return new SynchronousConnector();
	}

}
