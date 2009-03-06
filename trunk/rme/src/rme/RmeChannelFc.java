package rme;

import arcademis.*;
import arcademis.concreteComponents.*;

public class RmeChannelFc implements ChannelFc {

	private boolean reportSize = false;

	private boolean buffer = true;

	public Channel createChannel () {
		Channel c = null;

		if(buffer)
			c = new TcpBufferedSocketChannel_J2SE();
		else
			c = new TcpSocketChannel_J2SE();

		if(reportSize) {
			ReportSizeDecorator rsc = new ReportSizeDecorator(c);
			return rsc;
		} else {
			return c;
		}
	}

	public Channel createChannel (int objType) {
		Channel c = null;

		if(buffer)
			c = new TcpBufferedSocketChannel_J2SE();
		else
			c = new TcpSocketChannel_J2SE();

		if(reportSize) {
			ReportSizeDecorator rsc = new ReportSizeDecorator(c);
			return rsc;
		} else {
			return c;
		}
	}

	public Channel createChannel (java.net.Socket s) {
		Channel c = null;

		if(buffer)
			c = new TcpBufferedSocketChannel_J2SE(s);
		else
			c = new TcpSocketChannel_J2SE(s);

		if(reportSize) {
			ReportSizeDecorator rsc = new ReportSizeDecorator(c);
			return rsc;
		} else {
			return c;
		}
	}
}
