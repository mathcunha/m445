package rme.server;

import arcademis.Epid;
import arcademis.NetworkException;
import arcademis.server.ConnectionServer;
import arcademis.server.ConnectionServerFc;

public class RmeConServerFc implements ConnectionServerFc {

	public ConnectionServer createConnectionServer (Epid epid) {
		try {
			return new TcpSocketServer_J2SE(epid);
		} catch (NetworkException ne) {
			ne.printStackTrace();
		}
		return null;
	}

	public ConnectionServer createConnectionServer (int objType, Epid epid) {
		try {
			return new TcpSocketServer_J2SE(epid);
		} catch (NetworkException ne) {
			ne.printStackTrace();
		}
		return null;
	}
}
