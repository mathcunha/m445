package rme;

import java.io.*;
import java.net.*;
import arcademis.concreteComponents.*;

import arcademis.*;

/**
 * This class implements a communication channel based on the TCP/IP protocol
 * that is implemented by the Socket class of the J2SE platform.
 */
public class TcpSocketChannel_J2SE implements Channel
{
	protected OutputStream out = null;
	protected InputStream in = null;
	protected Socket sc = null;
	private HostPortEpid epid = null;

	public TcpSocketChannel_J2SE() {}

	public TcpSocketChannel_J2SE(Socket s) {
		try {
			sc = s;
			out = sc.getOutputStream();
			in = sc.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Makes a socket connection
	 * @param host The IP address of the target machine. Eg.:
	 * turmalina.dcc.ufmg.br
	 * @param port The port number on the target host.
	 */
	public void connect(Epid epi) throws NetworkException
	{
		try {
			String host = ((HostPortEpid)epi).getHostName();
			int port = ((HostPortEpid)epi).getPortNumber();
			sc = new Socket(host, port);
			out = sc.getOutputStream();
		    in = sc.getInputStream();
			this.epid = (HostPortEpid)OrbAccessor.getEpid();
			epid.setHostName(sc.getLocalAddress().getHostAddress());
			epid.setPortNumber(sc.getLocalPort());
		} catch (Exception e) {
			throw new NetworkException(e.getMessage());
		}
	}

	/**
	 * Causes the specified sequence of bytes to be written in the channel.
	 * Before sending the message, it is sent a set of four bytes representing the
	 * buffer size, that is, the size of the message.
	 * @param msg the message to be sent.
	 */
	public void send(byte[] msg) throws NetworkException
	{
		try {
			byte[] b = new byte[msg.length + 4];
			int bufSize = msg.length;
			b[0] = (byte)((bufSize >> 24) & 255);
			b[1] = (byte)((bufSize >> 16) & 255);
			b[2] = (byte)((bufSize >> 8) & 255);
			b[3] = (byte)(bufSize & 255);
			for(int i = 0; i < msg.length; i++)
				b[i+4] = msg[i];
			out.write(b);
		} catch (IOException ioE) {
			throw new NetworkException(ioE.getMessage());
		}
	}


	public byte[] recv() throws NetworkException {
		byte[] buf = null;
		try {
			int bufSize = readBufferSize();
			if(bufSize < 0)
				return null;
			buf = new byte[bufSize];
			in.read(buf);
		} catch (IOException ioE) {
			throw new NetworkException(ioE.getMessage());
		}
		return buf;
	}


	public void close()
	{
		try {
			in.close();
			out.close();
			sc.close();
		} catch (IOException ioE) {
			ioE.printStackTrace();
		}
	}

	public Epid getLocalEpid() {
		return this.epid;
	}

	public int readBufferSize() throws IOException {
		byte[] b = new byte[4];
		int status = in.read(b);
		if(status < 0)
			return -1;
		else {
			int i4 = (b[0] << 24) & 0xFF000000;
			int i3 = (b[1] << 16) & 0xFF0000;
			int i2 = (b[2] << 8) & 0xFF00;
			int i1 = b[3] & 0xFF;
			return (i4 + i3 + i2 + i1);
		}
	}


	public void setConnectionTimeout(int t) {
		try {
			sc.setSoTimeout(t);
		} catch (SocketException se) {
			se.printStackTrace();
		}
	}

	public int getConnectionTimeout() {
		int t = 0;
		try {
			t = sc.getSoTimeout();
		} catch (SocketException se) {
			se.printStackTrace();
		}
		return t;
	}
}