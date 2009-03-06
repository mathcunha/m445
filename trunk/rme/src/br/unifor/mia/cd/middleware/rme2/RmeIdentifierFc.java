package br.unifor.mia.cd.middleware.rme2;

import arcademis.*;
import arcademis.concreteComponents.*;

public class RmeIdentifierFc implements IdentifierFc {

	public Identifier createIdentifier () {
		return new HostTimeCountId();
	}

	public Identifier createIdentifier (int objType) {
		return new HostTimeCountId();
	}

	public Identifier createIdentifier (short discriminator) {
		return new HostTimeCountId(discriminator);
	}
}
