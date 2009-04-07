package proxy.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ProxyThread implements Runnable {
	Socket socket;

	public ProxyThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {

			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream2 = socket.getOutputStream();

			int bufferSize = 1024;
			byte buffer[] = new byte[bufferSize];
			int readBytes = inputStream.read(buffer);

			String host = "";
			String request = new String(buffer);
			int indice = request.indexOf("Host: ");
			if (indice > -1) {
				int indice2 = request.indexOf("\n", indice);
				host = request.substring(indice + 6, indice2 - 1);
			}

			String hostName = (host.split(":"))[0];
			String port = (host.split(":"))[1];

			// Implementar a política de seleção

			// Host remoto
			Socket connector = new Socket(hostName, new Integer(port));
			OutputStream outputStream = connector.getOutputStream();
			InputStream inputStream2 = connector.getInputStream();

			while (readBytes == bufferSize) {
				outputStream.write(buffer);
				readBytes = inputStream.read(buffer);
			}
			if (readBytes != -1) {
				outputStream.write(buffer, 0, readBytes);
				outputStream.flush();
				// running = false;
			}

			int bufferSize2 = 1024;
			byte buffer2[] = new byte[bufferSize2];																																										Thread.sleep(1000);
			int readBytes2 = inputStream2.read(buffer2);

			while (readBytes2 == bufferSize2) {
				outputStream2.write(buffer2);
				readBytes2 = inputStream2.read(buffer2);
				System.out.println("readBytes2: " + readBytes2);
			}
			if (readBytes2 != -1) {
				outputStream2.write(buffer2, 0, readBytes2);
				outputStream2.flush();
				// running = false;
			}

			socket.close();
			connector.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}