package rme;

import arcademis.*;
import arcademis.concreteComponents.*;

public class RmeEpidFc implements EpidFc, ArcademisConstants {

	public Epid createEpid () {
		HostPortEpid epid = new HostPortEpid();
		return epid;
	}

	public Epid createEpid (int objType) {
		switch(objType) {
			case HOST_PORT: return new HostPortEpid();
			default: return null;
		}
	}

	public Epid createEpid (String uri) {
		try {
			ParsedURL url = new ParsedURL(uri);
			return url.getEpid();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
