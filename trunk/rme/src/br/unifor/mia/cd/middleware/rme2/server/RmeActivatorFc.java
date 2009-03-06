package br.unifor.mia.cd.middleware.rme2.server;

import arcademis.server.*;

/**
 * The factory of ativator components. These components determine how a remote
 * object is made ready for receaving remote calls.
 */
public class RmeActivatorFc implements ActivatorFc {

	public Activator createActivator () {
		return new RmeActivator();
	}

	public Activator createActivator (int objType) {
		return new RmeActivator();
	}

}