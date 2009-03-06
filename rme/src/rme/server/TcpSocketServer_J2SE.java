package rme.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import rme.RmeChannelFc;

import arcademis.Channel;
import arcademis.Epid;
import arcademis.NetworkException;
import arcademis.ORB;
import arcademis.concreteComponents.HostPortEpid;
import arcademis.server.ConnectionServer;

public class TcpSocketServer_J2SE extends ConnectionServer {

	ServerSocket ss = null;

	Channel ch = null;

	public TcpSocketServer_J2SE(Epid epid) throws NetworkException {
		super(epid);
		try {
			int port = ((HostPortEpid) epid).getPortNumber();
			ss = new ServerSocket(port);
		} catch (Exception e) {
			throw new NetworkException(e.getMessage());
		}
	}

	public void accept() throws NetworkException {
		Socket s = null;
		try {
			s = ss.accept();
		} catch (Exception e) {
			throw new NetworkException("Error connection server: timeout = "
					+ getConnectionTimeout());
		}
		// creation of a channel:
		RmeChannelFc chFc = (RmeChannelFc) ORB.getChannelFactory();
		ch = chFc.createChannel(s);
	}

	public Channel getChannel() {
		return ch;
	}

	public void setConnectionTimeout(int t) {
		try {
			ss.setSoTimeout(t);
		} catch (SocketException se) {
			se.printStackTrace();
		}
	}

	public int getConnectionTimeout() {
		int t = 0;
		try {
			t = ss.getSoTimeout();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}
