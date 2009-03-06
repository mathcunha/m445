package br.unifor.mia.cd.middleware.rme2;

import arcademis.*;

public class RmeStreamFc implements StreamFc {

	public Stream createStream () {
		return new RmeStream();
	}

	public Stream createStream (int objType) {
		return new RmeStream();
	}

}